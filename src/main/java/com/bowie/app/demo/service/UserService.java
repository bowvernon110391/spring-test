package com.bowie.app.demo.service;

import com.bowie.app.demo.model.User;
import com.bowie.app.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        log.info("Saving user: {}", user);
        return userRepository.save(user);
    }

    public List<User> getAll() {
        log.info("Querying all users");
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id){
        log.info("Querying user by id: {}", id);
        return userRepository.findById(id);
    }

    public User getByUsername(String username) {
        log.info("Querying user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    public void deleteById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
    }

    public void delete(User user) {
        log.info("Deleting user: {}", user);
        userRepository.delete(user);
    }

    public long count() {
        return userRepository.count();
    }
}
