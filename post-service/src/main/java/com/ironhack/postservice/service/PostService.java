package com.ironhack.postservice.service;

//  import com.ironhack.postservice.dto.ContentDto;
import com.ironhack.postservice.dto.PostCreate;
import com.ironhack.postservice.enums.Theme;
import com.ironhack.postservice.exception.InputNotAllowed;
import com.ironhack.postservice.model.Post;
import com.ironhack.postservice.repository.PostRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    private static final Logger LOGGER = LogManager.getLogger(PostService.class);

    public Post createPost(PostCreate postCreate){
        LOGGER.info("Creating Post");
        return postRepository.save(new Post(postCreate.getUsername(), postCreate.getTheme(), postCreate.getCountry(), postCreate.getCity(), postCreate.getUrl(), postCreate.getContent(), postCreate.getTitle()));
    }

//    public void updateContent(Long id, ContentDto contentDto){
//        Post post = postRepository.findById(id).orElseThrow(() -> new InputNotAllowed("There's no post with id " + id));
//        post.setContent(contentDto.getContent());
//        postRepository.save(post);
//    }

    public void incrementKarma(Long id, String username){
        Post post = postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        LOGGER.info("Incrementing karma of post " + id);
        post.setKarma(post.getKarma()+1);
        post.getVotingUsers().add(username);
        postRepository.save(post);
    }

    public void decrementKarma(Long id, String username){
        LOGGER.info("Decrementing karma of post " + id);
        Post post = postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        post.setKarma(post.getKarma()-1);
        post.getVotingUsers().add(username);
        postRepository.save(post);
    }

    public void deletePost(Long id){
        LOGGER.info("Deleting post " + id);
        postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        postRepository.deleteById(id);
    }

    public List<Post> postsByCity(String cityId){
        String[] cityData = cityId.split("-");
        if (cityData.length!=2){
            throw new InputNotAllowed("Input must have the following structure: city-country");
        }
        LOGGER.info("Filtering posts by city");
        return postRepository.postsByCityAndCountry(cityData[0], cityData[1]);
    }

    public List<Post> postsByCityAndTheme(String cityId, Theme theme){
        String[] cityData = cityId.split("-");
        if (cityData.length!=2){
            throw new InputNotAllowed("Input must have the following structure: city-country");
        }
        LOGGER.info("Filtering posts by city and theme");
        return postRepository.postsByCityAndCountryAndTheme(cityData[0], cityData[1], theme);
    }

    public List<Post> postsByPerson(String username){
        LOGGER.info("Filtering posts by person");
        return postRepository.postsByPerson(username);
    }

    public List<Post> postsByPersonAndTheme(String username, Theme theme){
        LOGGER.info("Filtering posts by person and theme");
        return postRepository.postsByPersonAndTheme(username, theme);
    }
}
