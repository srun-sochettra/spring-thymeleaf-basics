package com.example.basicthymeleaf.service;

import com.example.basicthymeleaf.model.User;
import com.example.basicthymeleaf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findActiveUsers() {
        log.debug("Fetching all active users");
        return userRepository.findByIsActiveTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        log.debug("Saving new user: {}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        log.debug("Updating user with id: {}", id);
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Check email uniqueness only if changed
        if (!existing.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }

        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        existing.setPhone(user.getPhone());
        existing.setRole(user.getRole());
        existing.setAddress(user.getAddress());
        existing.setIsActive(user.getIsActive());

        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void toggleUserStatus(Long id) {
        log.debug("Toggling status for user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchUsers(String keyword) {
        log.debug("Searching users with keyword: {}", keyword);
        return userRepository.searchUsers(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveUsers() {
        return userRepository.countByIsActiveTrue();
    }
}