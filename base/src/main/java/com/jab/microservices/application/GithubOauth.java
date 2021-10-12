package com.jab.microservices.application;

public interface GithubOauth {

    GithubTokenResponse getTokenFromCode(String code);
}
