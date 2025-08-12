package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.dto.moodMusicMapping.MoodMusicMappingResponseDTO;
import com.rollingball211.moodify_backend.repository.MoodRepository;
import com.rollingball211.moodify_backend.service.MoodMusicMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mood-music")
public class MoodMusicMappingController {
    private final MoodMusicMappingService moodMusicMappingService;
    private final MoodRepository moodRepository;

    public MoodMusicMappingController(MoodMusicMappingService moodMusicMappingService, MoodRepository moodRepository) {
        this.moodMusicMappingService = moodMusicMappingService;
        this.moodRepository = moodRepository;
    }


    //MoodID로 음악 가져오기
    //Error Code 수정-> RuntimeException -> NOT FOUND가 되어야함 MOOD가 없는 경우 (ID 요청 에러)
    @GetMapping("/{moodId}")
    public ResponseEntity<List<MoodMusicMappingResponseDTO>> getMuisicByMood(@PathVariable Long moodId){
        return moodRepository.findById(moodId)
                .map(mood -> ResponseEntity.ok(moodMusicMappingService.getMusicByMood(mood)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}


