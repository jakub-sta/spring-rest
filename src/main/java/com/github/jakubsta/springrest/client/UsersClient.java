package com.github.jakubsta.springrest.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UsersClient {

    @Value("${github.api}")
    private String apiUrl;

    public Mono<User> getUserInfo(String login) {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.pathSegment("{login}")
                        .build(login))
                .retrieve()
                .bodyToMono(User.class);
    }
}
