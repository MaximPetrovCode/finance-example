package com.example.finance.controllers;

import com.example.finance.models.responses.CurrencyResponse;
import com.example.finance.models.responses.DataResponse;
import com.example.finance.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/")
public class MainController {

    @Autowired
    private DataService dataService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public List<DataResponse> main() {
        return dataService.getAllValues();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/values")
    public List<DataResponse> values() {
        return dataService.getAllValues();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/currency")
    public List<CurrencyResponse> currencyAll() {
        return dataService.getAllCurrencies();
    }

}
