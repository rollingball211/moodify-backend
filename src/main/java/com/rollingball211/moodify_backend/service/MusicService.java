package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Music;
import com.rollingball211.moodify_backend.repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//music

//id / url / artist
@Service
public class MusicService {
    private final MusicRepository musicRepository;

    public MusicService (MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    } //전체 음악 리스트 가져옴

    public Optional<Music> getMusicById(Long id) {
        return musicRepository.findById(id);
    } //musicId로 찾기

    public Music createMusic(Music music) {
        return musicRepository.save(music);
    }
}
