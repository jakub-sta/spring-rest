package com.github.jakubsta.springrest.service;

import com.github.jakubsta.springrest.client.User;
import com.github.jakubsta.springrest.client.UsersClient;
import com.github.jakubsta.springrest.dto.UserDto;
import com.github.jakubsta.springrest.repository.LoginRequestRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserHandler implements UserService {

    private final UsersClient client;
    private final LoginRequestRepository repository;

    public UserHandler(UsersClient client, LoginRequestRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    /**
     * @param login github user login
     * @return {@link UserDto} that contains user info with calculations
     * done in {@link #doCalculations(int, int)}
     */
    @Override
    public Mono<UserDto> get(String login) {
        return repository.incrementOrInsert(login)
                .then(client.getUserInfo(login))
                .map(this::convert)
                .log();
    }

    private double doCalculations(int followers, int publicRepos) {
        //ternary operator used to prevent division by 0
        return followers != 0 ? (6 / (double) followers * (2 + publicRepos)) : 0;
    }


    private UserDto convert(User user) {
        return new UserDto(user.getId(),
                user.getLogin(),
                user.getName(),
                user.getType(),
                user.getAvatarUrl(),
                user.getCreatedAt(),
                doCalculations(user.getFollowers(), user.getPublicRepos()));
    }
}
