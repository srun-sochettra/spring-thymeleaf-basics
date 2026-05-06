package com.example.basicthymeleaf.service;

import com.example.basicthymeleaf.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();

    List<User> findActiveUsers();

    Optional<User> findUserById(Long id);

    User saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    void toggleUserStatus(Long id);

    List<User> searchUsers(String keyword);

    boolean existsByEmail(String email);

    long countActiveUsers();
}