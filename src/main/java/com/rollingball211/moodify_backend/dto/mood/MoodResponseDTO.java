package com.rollingball211.moodify_backend.dto.mood;

public class MoodResponseDTO {
    private Long id;
    private String name; //happy , anger , blue... etc

    public MoodResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




