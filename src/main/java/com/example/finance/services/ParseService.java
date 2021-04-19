package com.example.finance.services;

import com.example.finance.repositories.CurrencyRepository;
import com.example.finance.repositories.DataRepository;
import com.example.finance.models.entities.CurrencyEntity;
import com.example.finance.models.entities.DataEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParseService {

    private final DataRepository dataRepository;

    private final CurrencyRepository currencyRepository;

    public ParseService(DataRepository dataRepository, CurrencyRepository currencyRepository) {
        this.dataRepository = dataRepository;
        this.currencyRepository = currencyRepository;
    }

    public void parse(String data, Long timestamp) {
        Document doc = Jsoup.parse(data);
        for (var table : doc.select("table.data")) {
            for (var row : table.select("tr")) {
                var tds = row.select("td");

                if (tds.isEmpty()) {
                    continue;
                }

                var currencyName = tds.get(1).text();
                if (currencyName.equalsIgnoreCase("EUR") || currencyName.equalsIgnoreCase("USD")) {
                    var currencyEntity = new CurrencyEntity();
                    currencyEntity.setCode(Integer.valueOf(tds.get(0).text()));
                    currencyEntity.setName(currencyName);
                    currencyEntity.setDescription(tds.get(3).text());

                    var dataEntity = new DataEntity();
                    dataEntity.setCurrency(currencyEntity);
                    dataEntity.setNums(Integer.valueOf(tds.get(2).text()));
                    dataEntity.setCourse(Double.parseDouble(tds.get(4).text().replace(",", ".")));
                    dataEntity.setTimestamp(timestamp);

                    this.save(currencyEntity, dataEntity);
                }
            }
        }
    }

    private void save(CurrencyEntity currencyEntity, DataEntity dataEntity) {
        Optional<CurrencyEntity> entityCurrency = currencyRepository.findById(currencyEntity.getCode());
        if (entityCurrency.isEmpty()) {
            currencyRepository.save(currencyEntity);
        }

        Optional<List<DataEntity>> oldsEntities = dataRepository.findData(dataEntity.getNums(), dataEntity.getCurrency().getCode(), dataEntity.getTimestamp());
        oldsEntities.ifPresent(dataEntities -> dataEntities.forEach(dataRepository::delete));
        dataRepository.save(dataEntity);
    }

}
