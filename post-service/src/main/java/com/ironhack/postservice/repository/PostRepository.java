package com.ironhack.postservice.repository;

import com.ironhack.postservice.enums.Theme;
import com.ironhack.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.city=:city AND p.country=:country ORDER BY p.karma DESC")
    public List<Post> postsByCityAndCountry(String city, String country);

    @Query("SELECT p FROM Post p WHERE p.city=:city AND p.country=:country AND p.theme=:theme ORDER BY p.karma DESC")
    public List<Post> postsByCityAndCountryAndTheme(String city, String country, Theme theme);

    @Query("SELECT p FROM Post p WHERE p.username=:username ORDER BY p.karma DESC")
    public List<Post> postsByPerson(String username);

    @Query("SELECT p FROM Post p WHERE p.username=:username And p.theme=:theme ORDER BY p.karma DESC")
    public List<Post> postsByPersonAndTheme(String username, Theme theme);
}
