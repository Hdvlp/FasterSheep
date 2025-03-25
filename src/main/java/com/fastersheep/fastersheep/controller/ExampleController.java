package com.fastersheep.fastersheep.controller;

import com.fastersheep.fastersheep.model.Currencies;
import com.fastersheep.fastersheep.model.ExampleEntity;
import com.fastersheep.fastersheep.service.ExampleService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExampleController {

    private final ExampleService service;


    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @GetMapping("/show/usd/f")
    public String getExampleEntitySortedByTimestampPartF() {

        List<ExampleEntity> l = service.getEntitySortedByTimestampPartF(Currencies.USD);

        String res = "";
        for (ExampleEntity exampleEntity : l){
            res += exampleEntity.toHtml() + " ";
        }
        return res;
    }

    @GetMapping("/show/g")
    public String getFirst20ExampleEntitySortedByTimestampPartG(
        @RequestParam(value = "currencies", 
            required = true) String currencies
        ) {
            Currencies currenciesGet = Currencies.USD;
            for (Currencies curr : Currencies.values()) { 
                if(curr.name().equalsIgnoreCase(currencies)){
                    currenciesGet = curr;
                    break;
                }
            }
            // if (currencies.equalsIgnoreCase("usd")) currenciesGet = Currencies.USD;
            // if (currencies.equalsIgnoreCase("hkd")) currenciesGet = Currencies.HKD;
            // if (currencies.equalsIgnoreCase("cny")) currenciesGet = Currencies.CNY;
            // if (currencies.equalsIgnoreCase("jpy")) currenciesGet = Currencies.JPY;
            // if (currencies.equalsIgnoreCase("gbp")) currenciesGet = Currencies.GBP;
        Page<ExampleEntity> l = service.getEntitySortedByTimestampPartG(currenciesGet);

        String res = "";
        for (ExampleEntity exampleEntity : l){
            res += exampleEntity.toHtml() + " ";
        }
        return res;
    }

}

