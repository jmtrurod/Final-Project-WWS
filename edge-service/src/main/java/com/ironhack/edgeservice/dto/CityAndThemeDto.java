package com.ironhack.edgeservice.dto;
import com.ironhack.edgeservice.enums.Theme;

public class CityAndThemeDto {
    private String cityId;
    private Theme theme;

    public CityAndThemeDto(){}

    public CityAndThemeDto(String cityId, Theme theme) {
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
