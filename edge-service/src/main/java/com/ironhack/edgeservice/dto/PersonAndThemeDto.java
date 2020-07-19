package com.ironhack.edgeservice.dto;
import com.ironhack.edgeservice.enums.Theme;

public class PersonAndThemeDto {
    private String username;
    private Theme theme;

    public PersonAndThemeDto(){}

    public PersonAndThemeDto(String username, Theme theme) {
        this.username = username;
        this.theme = theme;
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
}
