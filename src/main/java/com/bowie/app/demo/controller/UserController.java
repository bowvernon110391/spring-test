package com.bowie.app.demo.controller;

import com.bowie.app.demo.model.User;
import com.bowie.app.demo.model.specification.CommonTrait;
import com.bowie.app.demo.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    Page<User> index(@RequestParam(defaultValue = "", name = "q") String q,
                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                     Pageable pageable) {
        log.info("index by {} from {} to {}", q, startDate, endDate);

        // start by default spec (WHERE 1)
        Specification<User> spec = Specification.where(null);

        if (q.length() > 0) {
            spec = spec.and(
                    User.fullNameContains(q)
                            .or(User.usernameContains(q))
            );
        }

        if (startDate != null) {
//            spec = spec.and(User.from(startDate));
            spec = spec.and(CommonTrait.createdFrom(startDate));
        }

        if (endDate != null) {
//            spec = spec.and(User.to(endDate));
            spec = spec.and(CommonTrait.createdTo(endDate));
        }

        return userRepository.findAll(spec, pageable);
    }

    @GetMapping("/count")
    ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(userRepository.count());
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> all() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> show(@PathVariable Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userRepository.findById(id)
                .map(u -> {
                    u.setUsername(user.getUsername());
//                    u.setPassword(user.getPassword());
                    u.setFullName(user.getFullName());
                    return userRepository.save(u);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
        return ResponseEntity.ok().body(updatedUser);
    }
}
