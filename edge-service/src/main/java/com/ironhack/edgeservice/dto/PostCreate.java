package com.ironhack.edgeservice.dto;
import com.ironhack.edgeservice.enums.Theme;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCreate {

    private String username;
    private Theme theme;
    private String country;
    private String city;
    private String url;
    private String content;
    private String title;

    public PostCreate(){}

    public PostCreate(String username, Theme theme, String country, String city, String url, String content, String title) {
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
