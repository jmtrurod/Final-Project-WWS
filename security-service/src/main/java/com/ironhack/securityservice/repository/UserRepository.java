package com.ironhack.securityservice.repository;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAllByUsername(String username);

    List<User> findByRolEquals(Role rol);
}