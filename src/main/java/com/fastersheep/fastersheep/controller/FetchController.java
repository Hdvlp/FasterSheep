package com.fastersheep.fastersheep.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastersheep.fastersheep.model.Currencies;
import com.fastersheep.fastersheep.service.ExampleService;
import com.fastersheep.fastersheep.service.HttpService;

@RestController
@RequestMapping("/fetch")
public class FetchController{


    private final ExampleService service;
    private String cryptoData = getCryptoData();
    private long lastMillisec = Instant.now().toEpochMilli();

    private Map<String, Long> lastMillisecSaveMap = new HashMap<>();

    public FetchController(ExampleService service) {
        long lastMillisecSave = Instant.now().toEpochMilli();
        for (Currencies curr : Currencies.values()) {
            lastMillisecSaveMap.put(curr.name(),lastMillisecSave);
        }
        this.service = service;
    }


    @GetMapping("/save")
    public String saveEntityGBP(
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
        BigDecimal price = new BigDecimal(getCryptoPrice(currenciesGet));

        long diff = Instant.now().toEpochMilli() - lastMillisecSaveMap.get(currenciesGet.name());
        
        if (diff > 1000*250){
            try{
                Thread.sleep((long)(Math.random() * 1500 + 2050));
                service.saveEntity(currenciesGet, price);
                long lastMillisecSave = Instant.now().toEpochMilli();
                lastMillisecSaveMap.put(currenciesGet.name(),lastMillisecSave);
                return "OK";
            }catch(Exception _){}
        }
        else{
            return "Not OK. Time is not ready.";
        }
        return "Not OK. Maybe errors.";
    }


    public String getCryptoPrice(Currencies currencies) {
        String tmp = cryptoData;
        long diff = Instant.now().toEpochMilli() - lastMillisec;
        if (diff > 1000*250){
            try{
                Thread.sleep((long)(Math.random() * 1500 + 2050));
                tmp = getCryptoData();
                lastMillisec = Instant.now().toEpochMilli();
                cryptoData = tmp;
            }catch(Exception _){}
        }
        String usdString = String.format("\"%s\":{\"15m\":", currencies.name());
        int indexUsd = tmp.indexOf(usdString);
        int indexComma = tmp.indexOf(",", indexUsd);
        String sub = tmp.substring(indexUsd + usdString.length(), 
            indexComma);
        String allowed = "01234567890eE.";
        String subSanitized = "";
        for (int i=0; i < sub.length(); i++){
            if (allowed.indexOf(sub.charAt(i)) > -1)
                subSanitized += sub.charAt(i);
        }
        if (sub.length() == subSanitized.length()){
            return subSanitized;
        }
        return "";

    }

    public static String getCryptoData() {
        String tmp = "";
        try{
            HttpService httpService = new HttpService("https://blockchain.info/ticker");
            tmp = new String(httpService.getRes());
        } catch (Exception _){}

        return tmp;

    }

}
