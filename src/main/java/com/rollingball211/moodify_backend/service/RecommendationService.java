package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.domain.MoodMusicMapping;
import com.rollingball211.moodify_backend.domain.Music;
import com.rollingball211.moodify_backend.repository.MoodLogRepository;
import com.rollingball211.moodify_backend.repository.MoodMusicMappingRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final MoodLogRepository moodLogRepository;
    private final MoodMusicMappingRepository moodMusicMappingRepository;

    public RecommendationService (MoodLogRepository moodLogRepository, MoodMusicMappingRepository moodMusicMappingRepository) {
        this.moodLogRepository = moodLogRepository;
        this.moodMusicMappingRepository = moodMusicMappingRepository;
    }

    //해당 ID의 가장 최근 목록을 불러와서 음악을 추천해줌
    public List<Music> recommendedMusicByRecentMood(Long userId){
        //MoodLog의 Null을 check!
         MoodLog log = moodLogRepository.findTopByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found!" + userId));

         Mood mood = log.getMood();
         //Mood 기준으로 음악 매핑 조회하기
         List<MoodMusicMapping> mappings = moodMusicMappingRepository.findByMood(mood);
         //Music만 추출하기
         List<Music> musicList = mappings.stream()
                 .map(MoodMusicMapping::getMusic)
                 .collect(Collectors.toList());

         //랜덤 돌리기
        Collections.shuffle(musicList);
        return musicList.stream().limit(3).collect(Collectors.toList());

    }
}
