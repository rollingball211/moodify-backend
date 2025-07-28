package com.rollingball211.moodify_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MoodLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Mood mood;

    private LocalDateTime createdAt;
}
