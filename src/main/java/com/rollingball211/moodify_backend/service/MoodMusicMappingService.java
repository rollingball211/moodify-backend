package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.dto.moodMusicMapping.MoodMusicMappingResponseDTO;
import com.rollingball211.moodify_backend.repository.MoodMusicMappingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodMusicMappingService {

    private final MoodMusicMappingRepository moodMusicMappingRepository;

    public MoodMusicMappingService(MoodMusicMappingRepository moodMusicMappingRepository){
        this.moodMusicMappingRepository = moodMusicMappingRepository;
    }

    //무드별 음악 가져오기
    public List<MoodMusicMappingResponseDTO> getMusicByMood(Mood mood) {
        return moodMusicMappingRepository.findByMood(mood).stream()
                .map(MoodMusicMappingResponseDTO::new)
                .toList();
    }
}
