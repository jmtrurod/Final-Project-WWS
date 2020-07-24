package com.ironhack.postservice.service;

//  import com.ironhack.postservice.dto.ContentDto;
import com.ironhack.postservice.dto.PostCreate;
import com.ironhack.postservice.enums.Theme;
import com.ironhack.postservice.exception.InputNotAllowed;
import com.ironhack.postservice.model.Post;
import com.ironhack.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostCreate postCreate){
        return postRepository.save(new Post(postCreate.getUsername(), postCreate.getTheme(), postCreate.getCountry(), postCreate.getCity(), postCreate.getUrl(), postCreate.getContent(), postCreate.getTitle()));
    }

//    public void updateContent(Long id, ContentDto contentDto){
//        Post post = postRepository.findById(id).orElseThrow(() -> new InputNotAllowed("There's no post with id " + id));
//        post.setContent(contentDto.getContent());
//        postRepository.save(post);
//    }

    public void incrementKarma(Long id, String username){
        Post post = postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        post.setKarma(post.getKarma()+1);
        post.getVotingUsers().add(username);
        postRepository.save(post);
    }

    public void decrementKarma(Long id, String username){
        Post post = postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        post.setKarma(post.getKarma()-1);
        post.getVotingUsers().add(username);
        postRepository.save(post);
    }

    public void deletePost(Long id){
        postRepository.findById(id).orElseThrow(()-> new InputNotAllowed("" + id + " doesn't exist"));
        postRepository.deleteById(id);
    }

    public List<Post> postsByCity(String cityId){
        String[] cityData = cityId.split("-");
        if (cityData.length!=2){
            throw new InputNotAllowed("Input must have the following structure: city-country");
        }
        return postRepository.postsByCityAndCountry(cityData[0], cityData[1]);
    }

    public List<Post> postsByCityAndTheme(String cityId, Theme theme){
        String[] cityData = cityId.split("-");
        if (cityData.length!=2){
            throw new InputNotAllowed("Input must have the following structure: city-country");
        }
        return postRepository.postsByCityAndCountryAndTheme(cityData[0], cityData[1], theme);
    }

    public List<Post> postsByPerson(String username){
        return postRepository.postsByPerson(username);
    }

    public List<Post> postsByPersonAndTheme(String username, Theme theme){
        return postRepository.postsByPersonAndTheme(username, theme);
    }
}
