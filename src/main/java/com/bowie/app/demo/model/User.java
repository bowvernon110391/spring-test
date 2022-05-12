package com.bowie.app.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return String.format("id=%d,username=%s,password=%s,fullName=%s,createdAt=%s,updatedAt=%s",
                id, username, password, fullName, createdAt, updatedAt);
    }

    // SCOPES
    public static Specification<User> usernameContains(String q) {
        return (user, cq, cb) -> cb.like(user.get("username"), "%" + q + "%");
    }

    public static Specification<User> fullNameContains(String q) {
        return (user, cq, cb) -> cb.like(user.get("fullName"), "%" + q + "%");
    }

    public static Specification<User> from(Date date) {
        return (user, cq, cb) -> cb.greaterThanOrEqualTo(cb.function("DATE", Date.class, user.get("createdAt")), date);
    }

    public static Specification<User> to(Date date) {
        return (user, cq, cb) -> cb.lessThanOrEqualTo(cb.function("DATE", Date.class, user.get("createdAt")), date);
    }
}
