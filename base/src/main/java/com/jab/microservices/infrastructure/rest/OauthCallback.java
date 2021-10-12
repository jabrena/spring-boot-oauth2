package com.jab.microservices.infrastructure.rest;

import com.jab.microservices.application.GithubOauth;
import com.jab.microservices.application.GithubTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class OauthCallback {

    Logger logger = LoggerFactory.getLogger(OauthCallback.class);

    @Autowired
    private GithubOauth githubOauth;

    @GetMapping("callback")
    public RedirectView callback(@RequestParam("code") String code) {
        logger.info(code);

        githubOauth.getTokenFromCode(code);

        return new RedirectView("callback.html");
    }

}
