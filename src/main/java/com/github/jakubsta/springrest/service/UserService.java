package com.github.jakubsta.springrest.service;


import com.github.jakubsta.springrest.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> get(String login);

}
