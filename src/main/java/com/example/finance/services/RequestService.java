package com.example.finance.services;

import com.example.finance.DateUtils;
import com.example.finance.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class RequestService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private ParseService parseService;

    @PostConstruct
    private void init() {
        if (dataRepository.count() < DateUtils.DAYS_IN_MONTHS) {
            requestPageForDates();
        }
    }

    public String request(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private String requestPage(String date) throws ParseException, IOException, InterruptedException {
        String url = "https://www.cbr.ru/currency_base/daily/?UniDbQuery.Posted=True&UniDbQuery.To=" + date;
        return this.request(url);
    }

    public void requestPageForDates() {
        ArrayList<String> dates = DateUtils.getPreviousDaysFromNow();
        dates.forEach(date -> {
            try {
                var data = requestPage(date);
                this.parseService.parse(data, DateUtils.getTimestamp(date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /***
     * Gets new data every night at 12:00 am.
     */
    @Scheduled(fixedDelay = 1000, cron = "0 0 0 * * ?")
    private void updateData() {
        Date today = new Date();
        String dateString = DateUtils.timestampToDateString(today.getTime());
        try {
            this.requestPage(dateString);
        } catch (ParseException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
