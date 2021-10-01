package com.github.jakubsta.springrest.controller;


import com.github.jakubsta.springrest.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.jakubsta.springrest.service.UserService;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("users/{login}")
    public Mono<UserDto> get(@PathVariable("login") final String login) {
        return service.get(login);
    }
}
