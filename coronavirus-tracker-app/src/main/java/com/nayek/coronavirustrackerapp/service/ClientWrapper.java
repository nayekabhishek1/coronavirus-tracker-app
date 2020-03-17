package com.nayek.coronavirustrackerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientWrapper {

    private RestTemplate restTemplate;

    <T> ResponseEntity<T> retrieveCases(final String url, Class<T> type) {

        return restTemplate.getForEntity(url, type);

    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
