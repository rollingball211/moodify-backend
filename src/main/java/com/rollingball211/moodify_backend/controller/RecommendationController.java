package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Music;
import com.rollingball211.moodify_backend.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//음악 추천 컨트롤러
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    // Field DI
    private final RecommendationService recommendationService;

    public RecommendationController (RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    //URL 경로에서 userId 추출
    @GetMapping("/{userId}")
    public ResponseEntity<List<Music>> recommend(@PathVariable Long userId) {
        List<Music> recommend = recommendationService.recommendedMusicByRecentMood(userId);
        return ResponseEntity.ok(recommend);
    }
}
