package com.ironhack.postservice.dto;

import com.ironhack.postservice.enums.Theme;

import javax.validation.constraints.NotNull;

public class PersonAndThemeDto {
    @NotNull
    private String username;
    @NotNull
    private Theme theme;

    public PersonAndThemeDto(){}

    public PersonAndThemeDto(@NotNull String username, @NotNull Theme theme) {
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
