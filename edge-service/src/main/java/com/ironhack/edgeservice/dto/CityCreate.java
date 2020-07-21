package com.ironhack.edgeservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityCreate {
    private String city;
    private String country;
    private byte[] pic;
    private String description;

    public CityCreate(){}

    public CityCreate(String description) {
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
