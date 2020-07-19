package com.ironhack.postservice.dto;

import com.ironhack.postservice.enums.Theme;

import javax.validation.constraints.NotNull;

public class CityAndThemeDto {
    @NotNull
    private String cityId;
    @NotNull
    private Theme theme;

    public CityAndThemeDto(){}

    public CityAndThemeDto(@NotNull String cityId, @NotNull Theme theme) {
        this.cityId = cityId;
        this.theme = theme;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
