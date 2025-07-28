package com.rollingball211.moodify_backend.domain;

import jakarta.persistence.*;

@Entity
public class MoodMusicMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mood mood;

    @ManyToOne
    private Music music;
}
