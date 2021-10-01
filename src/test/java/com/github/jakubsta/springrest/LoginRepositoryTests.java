package com.github.jakubsta.springrest;

import com.github.jakubsta.springrest.repository.LoginRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoginRepositoryTests {

    @Autowired
    private LoginRequestRepository repository;

    @AfterEach
    public void eraseDB() {
        repository.deleteAll().block();
    }

    @Test
    public void testRepositoryBeanExists() {
        assertNotNull(repository);
    }

    @Test
    public void whenInsert1Then1IsExpected() {
        repository.incrementOrInsert("jakub-sta")
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void whenInsertAndIncrementThen1Expected() {
        repository.incrementOrInsert("jakub-sta")
             .then(repository.incrementOrInsert("jakub-sta"))
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void whenInsertAndIncrementThen2RequestsAreExpected() {
        repository.incrementOrInsert("jakub-sta")
                .then(repository.incrementOrInsert("jakub-sta"))
                .then(repository.findById("jakub-sta"))
                .as(StepVerifier::create)
                .expectNextMatches(loginRequest -> loginRequest.getRequestCount() == 2)
                .verifyComplete();
    }
}
