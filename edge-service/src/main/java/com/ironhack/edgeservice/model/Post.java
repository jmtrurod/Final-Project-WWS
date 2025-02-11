package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.Theme;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private Long id;
    private String username;
    private Theme theme;
    private String country;
    private String city;
    private String url;
    private int karma;
    private String content;
    private String title;
    private List<String> votingUsers;

    public Post() {}

    public Post(String username, Theme theme, String country, String city, String url, String content, String title) {
        this.username = username;
        this.theme = theme;
        this.country = country;
        this.city = city;
        this.url = url;
        this.content = content;
        this.title = title;
        this.karma = 0;
        this.votingUsers = new ArrayList<String>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
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

    public List<String> getVotingUsers() {
        return votingUsers;
    }

    public void setVotingUsers(List<String> votingUsers) {
        this.votingUsers = votingUsers;
    }
}
