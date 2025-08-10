package com.rollingball211.moodify_backend.dto.moodMusicMapping;

import com.rollingball211.moodify_backend.domain.MoodMusicMapping;

public class MoodMusicMappingResponseDTO {
    //mood
    private Long moodId;
    private String moodName;
    //Music
    private Long musicId;
    private String musicTitle;
    private String musicArtist;
    private String musicUrl;

    public MoodMusicMappingResponseDTO(MoodMusicMapping moodMusicMapping){
        this.moodId = moodMusicMapping.getMood().getId();
        this.moodName = moodMusicMapping.getMood().getName();

        this.musicId = moodMusicMapping.getMusic().getId();
        this.musicTitle = moodMusicMapping.getMusic().getTitle();
        this.musicArtist = moodMusicMapping.getMusic().getArtist();
        this.musicUrl = moodMusicMapping.getMusic().getUrl();
    }

    public Long getMoodId() {
        return moodId;
    }

    public void setMoodId(Long moodId) {
        this.moodId = moodId;
    }

    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }

    public Long getMusicId() {
        return musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}
