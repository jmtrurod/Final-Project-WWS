//package com.jediorderofoperations.securityservice.controller.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jediorderofoperations.securityservice.model.User;
//import com.jediorderofoperations.securityservice.enums.Role;
//import com.jediorderofoperations.securityservice.repository.UserRepository;
//import com.jediorderofoperations.securityservice.security.CustomSecurityUser;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//class UserControllerImplTest {
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    private CustomSecurityUser admin;
//    private CustomSecurityUser salesPerson;
//    ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
//        admin = new CustomSecurityUser(new User("admin", "admin", Role.ADMIN));
//        salesPerson = new CustomSecurityUser(new User("salesperson", "salesperson", Role.SALES_PERSON));
//        userRepository.save(new User("testUser", "1234", Role.SALES_PERSON));
//    }
//
//    @AfterEach
//    void tearDown() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void isSalesPerson() throws Exception {
//        assertEquals(Boolean.toString(true), mockMvc.perform(get("/users/is-salesperson").with(user(salesPerson)))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString());
//    }
//
//    @Test
//    void isAdmin() throws Exception {
//        assertEquals(Boolean.toString(true), mockMvc.perform(get("/users/is-admin").with(user(admin)))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString());
//    }
//
//    @Test
//    void createSalesPerson() throws Exception {
//        User user = new User("manolo", "manolo", Role.SALES_PERSON);
//
//        String json = mapper.writeValueAsString(user);
//        mockMvc.perform((post("/users/salesperson").with(user(admin)))
//                .contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.username" ).value("manolo"))
//                .andExpect(jsonPath("$.rol" ).value("SALES_PERSON"));
//    }
//
//    @Test
//    void createSalesPerson_AlreadyExisting() throws Exception {
//        User user = new User("salesperson", "salesperson", Role.SALES_PERSON);
//        String json = mapper.writeValueAsString(user);
//        mockMvc.perform((post("/users/salesperson").with(user(admin)))
//                .contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isCreated());
//        assertTrue(mockMvc.perform((post("/users/salesperson").with(user(admin)))
//                .contentType(MediaType.APPLICATION_JSON).content(json))
//                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString().isEmpty());
//    }
//
//    @Test
//    void viewAllSalesPerson() throws Exception {
//        mockMvc.perform(get("/users/salesperson").with(user(salesPerson)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].username").value("testUser"))
//                .andExpect(jsonPath("$[0].rol").value("SALES_PERSON"));
//    }
//
//    @Test
//    void viewAllUsers() throws Exception {
//        mockMvc.perform(get("/users").with(user(admin)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].username").value("testUser"))
//                .andExpect(jsonPath("$[0].rol").value("SALES_PERSON"));
//    }
//}