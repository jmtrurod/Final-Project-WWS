package com.ironhack.edgeservice.model;


public class City {
    private String id;
    private String city;
    private String country;
    private String pic;
    private String description;

    public City(){}

    public City(String city, String country, String pic, String description) {
        this.city = city;
        this.country = country;
        this.pic = pic;
        this.description = description;
        this.id = this.city + "-" + this.country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
