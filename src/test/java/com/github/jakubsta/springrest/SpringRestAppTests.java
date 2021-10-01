package com.github.jakubsta.springrest;

import com.github.jakubsta.springrest.client.User;
import com.github.jakubsta.springrest.client.UsersClient;
import com.github.jakubsta.springrest.controller.UserController;
import com.github.jakubsta.springrest.dto.UserDto;
import com.github.jakubsta.springrest.repository.LoginRequestRepository;
import com.github.jakubsta.springrest.service.UserHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
@Import({UserHandler.class})
public class SpringRestAppTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LoginRequestRepository repository;

    @MockBean
    private UsersClient usersClient;

    @Test
    public void whenGetThenReturnUserDtoWithDivisionBy0Variant() {
        User user = new User(3, "jakub-sta", "Kuba", "USER",
                "http://madeupurl.com/3.jpg", "25.03.2018", 0, 2);
        Mono<User> userMono = Mono.just(user);

        when(usersClient.getUserInfo("jakub-sta")).thenReturn(userMono);
        when(repository.incrementOrInsert("jakub-sta")).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/users/jakub-sta")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(UserDto::calculations, equalTo(0.0));
    }

    @Test
    public void whenGetThenReturnUserDtoWithCalculations() {
        User user = new User(3, "jakub-sta", "Kuba", "USER",
                "", "", 2, 2);
        Mono<User> userMono = Mono.just(user);

        when(usersClient.getUserInfo("jakub-sta")).thenReturn(userMono);
        when(repository.incrementOrInsert("jakub-sta")).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/users/jakub-sta")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto.class)
                .value(UserDto::calculations, equalTo(12.0));
    }
}
