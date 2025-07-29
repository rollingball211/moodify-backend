package com.rollingball211.moodify_backend.dto;

public class MoodLogRequestDTO {
    private Long userId;
    private Long moodId;

    public MoodLogRequestDTO() {}

    public MoodLogRequestDTO(Long userId, Long moodId) {
        this.userId = userId;
        this.moodId = moodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMoodId() {
        return moodId;
    }

    public void setMoodId(Long moodId) {
        this.moodId = moodId;
    }
}
