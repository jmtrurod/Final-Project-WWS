package com.ironhack.userservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class User {
    @Id
    private String username;
    private String name;
    private String bio;
    @Lob
    private byte[] pic;
    private String mail;
    private int karma;

    public User(){}

    public User(String username, String name, String bio, byte[] pic, String mail) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.pic = pic;
        this.mail = mail;
        this.karma = 0;
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

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }
}
