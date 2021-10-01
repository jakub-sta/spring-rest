package com.github.jakubsta.springrest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(long id, String login, String name, String type,
                      String avatarUrl, String createdAt, double calculations) {
}
