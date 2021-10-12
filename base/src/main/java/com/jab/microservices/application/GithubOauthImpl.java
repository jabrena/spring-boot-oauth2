package com.jab.microservices.application;

import com.jab.microservices.infrastructure.rest.OauthCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubOauthImpl implements GithubOauth {

    Logger logger = LoggerFactory.getLogger(GithubOauthImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${step2_url}")
    private String step2_url;

    @Value("${client_id}")
    private String client_id;

    @Value("${client_secret}")
    private String client_secret;

    @Override
    public GithubTokenResponse getTokenFromCode(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", client_id);
        requestBody.add("client_secret", client_secret);
        requestBody.add("code", code);

        HttpEntity formEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GithubTokenResponse> response =
                restTemplate.exchange(step2_url, HttpMethod.POST, formEntity, GithubTokenResponse.class);

        logger.info(response.getBody().toString());

        return response.getBody();
    }
}
