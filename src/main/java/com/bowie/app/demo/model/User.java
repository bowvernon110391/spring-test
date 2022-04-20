package com.bowie.app.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    @PrePersist
    void prePersist() {
        setCreatedAt(Date.from(Instant.now()));
        setUpdatedAt(Date.from(Instant.now()));
    }

    @PreUpdate
    void preUpdate() {
        setUpdatedAt(Date.from(Instant.now()));
    }
}
