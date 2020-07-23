//package com.jediorderofoperations.securityservice.service;
//
//import com.jediorderofoperations.securityservice.model.User;
//import com.jediorderofoperations.securityservice.enums.Role;
//import com.jediorderofoperations.securityservice.repository.UserRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static java.util.Arrays.stream;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UserServiceTest {
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//
//    private List<User> users;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        User user = new User("pepe", "pepe", Role.SALES_PERSON);
//        User user2 = new User("antonio", "antonio", Role.SALES_PERSON);
//        users = Stream.of(user,user2).collect(Collectors.toList());
//        userRepository.saveAll(Stream.of(user,user2).collect(Collectors.toList()));
//    }
//
//    @AfterEach
//    void tearDown() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void loadUserByUsername() {
//        UserDetails customUser = userService.loadUserByUsername("pepe");
//        assertEquals(1, customUser.getAuthorities().size());
//        assertTrue(customUser.isAccountNonExpired());
//        assertTrue(customUser.isAccountNonLocked());
//        assertTrue(customUser.isCredentialsNonExpired());
//        assertTrue(customUser.isEnabled());
//    }
//
//    @Test
//    void loadUserByUsername_UserNotExist_Exception() {
//        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("ajhljkhfgasg"));
//    }
//
//    @Test
//    void createUser() {
//        User user = new User("manolo", "manolo", Role.SALES_PERSON);
//        User result = userService.createUser(user);
//        assertEquals("manolo", result.getUsername());
//        System.out.println(result.getPassword());
//        assertTrue(result.getPassword().matches("\\$\\w{2}\\$\\w{2}\\$.{53}"));
//    }
//
//    @Test
//    void createUser_UserExists_ThrowException() {
//        User user = new User("pepe", "pepe", Role.SALES_PERSON);
//        assertNull(userService.createUser(user));
//
//    }
//
//    @Test
//    void viewAllSalesPerson() {
//        List<User> result = userService.viewAllSalesPerson();
//        assertIterableEquals(users, result);
//
//    }
//}