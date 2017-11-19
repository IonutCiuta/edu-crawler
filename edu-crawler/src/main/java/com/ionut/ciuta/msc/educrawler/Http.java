package com.ionut.ciuta.msc.educrawler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * ionutciuta24@gmail.com on 18.11.2017.
 */
public class Http {
    private static final RestTemplate httpClient = new RestTemplate();

    public static String get(String url) {
        ResponseEntity<String> response = httpClient.getForEntity(url, String.class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed @ " + url);
        }

        return response.getBody();
    }
}
