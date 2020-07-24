package com.ironhack.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonCreate {
    @NotNull
    @Size(min = 6, max = 15, message = "Username must be composed by 6-15 characters")
    private String username;
    @NotNull
    @Size(min = 12, max = 60, message = "Name must have be composed by 12-60 characters")
    private String name;
    @NotNull
    @Size(min = 5, max = 140, message = "Biography must have be composed by 5-140 characters")
    private String bio;
    @NotNull
    private String pic;
    @NotNull
    @Email(message = "Enter valid email")
    private String mail;

    public PersonCreate(){}

    public PersonCreate(@NotNull @Size(min = 6, max = 15, message = "Username must be composed by 6-15 characters") String username, @NotNull @Size(min = 12, max = 60, message = "Name must have be composed by 12-60 characters") String name, @NotNull @Size(min = 5, max = 140, message = "Biography must have be composed by 5-140 characters") String bio, @NotNull String pic, @NotNull @Email(message = "Enter valid email") String mail) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.pic = pic;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
