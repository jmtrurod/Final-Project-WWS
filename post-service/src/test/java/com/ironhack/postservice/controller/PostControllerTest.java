package com.ironhack.postservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.postservice.dto.PostCreate;
import com.ironhack.postservice.enums.Theme;
import com.ironhack.postservice.model.Post;
import com.ironhack.postservice.repository.PostRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostControllerTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach()
    void afterEach(){
        postRepository.deleteAll();
    }

    @Test
    void createPost() throws Exception {
        PostCreate post = new PostCreate("juanjito556", Theme.CULTURE, "Spain", "Madrid", "", "Esto debe ser un contenido de varias palabras, no pocas",  "Título");
        mockMvc.perform(post("/posts/create")
                .content(objectMapper.writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void incrementKarma() throws Exception {
        Post post = new Post("juanjito556", Theme.CULTURE, "Spain", "Madrid", "", "Esto debe ser un contenido de varias palabras, no pocas",  "Título");
        postRepository.save(post);
        mockMvc.perform(put("/posts/increment/" + postRepository.findAll().get(0).getId() + "?username=pepito"))
                .andExpect(status().is2xxSuccessful());
        assertEquals(1, postRepository.findAll().get(0).getKarma());
    }

    @Test
    void decrementKarma() throws Exception {
        Post post = new Post("juanjito556", Theme.CULTURE, "Spain", "Madrid", "", "Esto debe ser un contenido de varias palabras, no pocas",  "Título");
        postRepository.save(post);
        mockMvc.perform(put("/posts/decrement/" + postRepository.findAll().get(0).getId() + "?username=pepito"))
                .andExpect(status().is2xxSuccessful());
        assertEquals(0, postRepository.findAll().get(0).getKarma());
    }
}