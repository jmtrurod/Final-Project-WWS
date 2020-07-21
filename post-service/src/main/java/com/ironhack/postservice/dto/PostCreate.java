package com.ironhack.postservice.dto;

import com.ironhack.postservice.enums.Theme;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCreate {

    @NotNull
    private String username;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Theme theme;
    @NotNull
    private String country;
    @NotNull
    private String city;
    private String url;
    @NotNull
    @Size(min = 20, max = 300, message = "Post size must be 20-300 characters")
    private String content;
    @NotNull
    @Size(min = 4, max = 25, message = "Post-title size must be 4-25 characters")
    private String title;

    public PostCreate(){}

    public PostCreate(@NotNull String username, @NotNull Theme theme, @NotNull String country, @NotNull String city, String url, @NotNull @Size(min = 20, max = 300, message = "Post size must be 20-300 characters") String content, @NotNull @Size(min = 4, max = 25, message = "Post-title size must be 4-25 characters") String title) {
        this.username = username;
        this.theme = theme;
        this.country = country;
        this.city = city;
        this.url = url;
        this.content = content;
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
