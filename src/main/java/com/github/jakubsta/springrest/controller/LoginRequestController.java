package com.github.jakubsta.springrest.controller;

import com.github.jakubsta.springrest.repository.LoginRequest;
import com.github.jakubsta.springrest.repository.LoginRequestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("requests")
public class LoginRequestController {

    private final LoginRequestRepository repository;

    public LoginRequestController(LoginRequestRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{login}")
    public Mono<LoginRequest> getRequests(@PathVariable("login") String login) {
        return repository.findById(login);
    }
}
