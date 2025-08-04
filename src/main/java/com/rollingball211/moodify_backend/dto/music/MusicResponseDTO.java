package com.rollingball211.moodify_backend.dto.music;

import java.util.List;

public class MusicResponseDTO {
    private Long id;
    private String title;
    private String artist;
    private String url;

    //추후 업데이트 예정
    /**
    private String genre;
    private String albumImageUrl;
    private List<String> moods;
**/
    public MusicResponseDTO(Long id, String title, String artist, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
