package com.github.jakubsta.springrest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class User {

    @JsonProperty("id")
    private long id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("followers")
    private int followers;
    @JsonProperty("public_repos")
    private int publicRepos;

    public User() {

    }

    public User(long id, String login, String name, String type,
                String avatarUrl, String createdAt, int followers, int publicRepos) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.followers = followers;
        this.publicRepos = publicRepos;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && followers == user.followers
                && publicRepos == user.publicRepos
                && Objects.equals(login, user.login)
                && Objects.equals(name, user.name)
                && Objects.equals(type, user.type)
                && Objects.equals(avatarUrl, user.avatarUrl)
                && Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, type, avatarUrl, createdAt, followers, publicRepos);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", followers=" + followers +
                ", publicRepos=" + publicRepos +
                '}';
    }
}
