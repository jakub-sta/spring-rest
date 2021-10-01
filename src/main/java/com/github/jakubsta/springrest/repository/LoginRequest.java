package com.github.jakubsta.springrest.repository;

import org.springframework.data.annotation.Id;

public class LoginRequest {

    @Id
    private String login;
    private int requestCount;

    public LoginRequest() {

    }

    public LoginRequest(String login, int requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "login='" + login + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
