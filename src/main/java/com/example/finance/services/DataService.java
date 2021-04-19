package com.example.finance.services;

import com.example.finance.repositories.CurrencyRepository;
import com.example.finance.repositories.DataRepository;
import com.example.finance.models.responses.CurrencyResponse;
import com.example.finance.models.responses.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<DataResponse> getAllValues() {
        var all = dataRepository.getAll();
        return all.map(dataEntities -> dataEntities.stream().map(entity -> {
            var dataResponse = new DataResponse();
            dataResponse.setCourse(entity.getCourse());
            dataResponse.setId(entity.getId());
            dataResponse.setNums(entity.getNums());
            dataResponse.setTimestamp(entity.getTimestamp());
            dataResponse.setCurrencyCode(entity.getCurrency().getCode());
            dataResponse.setCurrencyName(entity.getCurrency().getName());
            dataResponse.setCurrencyDescription(entity.getCurrency().getDescription());
            return dataResponse;
        }).collect(Collectors.toList())).orElse(null);
    }

    public List<CurrencyResponse> getAllCurrencies() {
        var all = currencyRepository.getAll();
        return all.map(currencyEntities -> currencyEntities.stream().map(currencyEntity -> {
            var currencyResponse = new CurrencyResponse();
            currencyResponse.setCode(currencyEntity.getCode());
            currencyResponse.setName(currencyEntity.getName());
            currencyResponse.setDescription(currencyEntity.getDescription());
            return currencyResponse;
        }).collect(Collectors.toList())).orElse(null);
    }

}
