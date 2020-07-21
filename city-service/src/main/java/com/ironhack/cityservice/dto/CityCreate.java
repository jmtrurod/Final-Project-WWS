package com.ironhack.cityservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityCreate {
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private byte[] pic;
    @NotNull
    @Size(min = 50, max = 350, message = "City description must be composed by 50-350 characters")
    private String description;

    public CityCreate(){}

    public CityCreate(@NotNull String city, @NotNull String country, @NotNull byte[] pic, @NotNull @Size(min = 50, max = 350, message = "City description must be composed by 50-350 characters") String description) {
        this.city = city;
        this.country = country;
        this.pic = pic;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
