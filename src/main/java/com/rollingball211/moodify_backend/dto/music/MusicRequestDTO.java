package com.rollingball211.moodify_backend.dto.music;

public class MusicRequestDTO {
    private String title;
    private String artist;
    private String url;

    public MusicRequestDTO() {}

    public MusicRequestDTO(String title, String artist, String url) {
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    // Getter/Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}