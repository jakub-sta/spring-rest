package com.github.jakubsta.springrest.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LoginRequestRepository extends ReactiveCrudRepository<LoginRequest, String> {

    @Query("INSERT INTO LOGIN_REQUEST (LOGIN, REQUEST_COUNT) VALUES(:login, 1) ON DUPLICATE KEY UPDATE REQUEST_COUNT=REQUEST_COUNT+1")
    Mono<Void> incrementOrInsert(String login);

}
