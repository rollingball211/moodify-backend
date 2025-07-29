package com.rollingball211.moodify_backend.dto;

import java.time.LocalDateTime;

public class MoodLogResponseDTO {
    private Long id;
    private String username;
    private String moodName;
    private LocalDateTime createdAt;

    public MoodLogResponseDTO() {}

    public MoodLogResponseDTO(Long id, String username, String moodName, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.moodName = moodName;
        this.createdAt = createdAt;
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
