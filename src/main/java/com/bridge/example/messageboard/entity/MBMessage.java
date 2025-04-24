package com.bridge.example.messageboard.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="messageboard_messages")

public class MBMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Double userRating;

    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public MBMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MBMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Double getUserRating() {
        return userRating;
    }

    public Double setUserRating(Double userRating) {
        this.userRating = userRating;
        return userRating;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }


}

