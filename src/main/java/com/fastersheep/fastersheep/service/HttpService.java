package com.fastersheep.fastersheep.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class HttpService {
    private String res;
    public HttpService(String uri){
        try{
            CompletableFuture<String> c =  get(uri);
            this.res =  new String(c.get());
        }
        catch (Exception ex){}
    }
    public String getRes(){
        return this.res;
    }
    public CompletableFuture<String> get(String uri) {
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .headers(
                "User-Agent", "Example user agent",
                "sec-ch-ua","\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"",
                "sec-ch-ua-mobile", "?0",
                "sec-ch-ua-platform", "Windows",
                "sec-fetch-dest", "image",
                "sec-fetch-mode", "no-cors",
                "sec-fetch-site", "same-origin",
                "user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36"
            )
            .build();

        return client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
    }

}
