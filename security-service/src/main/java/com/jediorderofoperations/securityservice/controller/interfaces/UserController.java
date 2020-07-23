package com.jediorderofoperations.securityservice.controller.interfaces;

import com.jediorderofoperations.securityservice.model.User;

import java.util.List;

/**
 * User controller interface
 */
public interface UserController {
    /**
     * Is User a SalesPerson
     * @param user User user
     * @return boolean
     */
    boolean isSalesPerson(User user);

    /**
     * Is User an Admin
     * @param user User user
     * @return boolean
     */
    boolean isAdmin(User user);

    /**
     * Create SalesPerson
     * @param salesPerson User salesPerson
     * @return User
     */
    User createSalesPerson(User salesPerson);

    /**
     * Get all Users
     * @return List of Users
     */
    List<User> viewAllUsers();

    /**
     * Get all SalesPersons
     * @return List of Users
     */
    List<User> viewAllSalesPerson();
}
