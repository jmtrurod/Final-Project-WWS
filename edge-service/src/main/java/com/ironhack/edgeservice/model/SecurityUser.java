package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.Role;

import java.util.Objects;

public class SecurityUser {
    private Long id;
    private String username;
    private String password;
    private Role rol;

    public SecurityUser() {
    }

    public SecurityUser(String username, String password, Role rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityUser user = (SecurityUser) o;
        return Objects.equals(username, user.username) &&
                rol == user.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, rol);
    }
}
