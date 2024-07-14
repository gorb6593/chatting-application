package org.example.chatchatpractice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    // Constructors, getters, and setters

    public User() {}

    public User(String username) {
        this.username = username;
    }

    // Getters and setters
}
