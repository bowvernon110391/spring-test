package com.bowie.app.demo.repo;

import com.bowie.app.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
