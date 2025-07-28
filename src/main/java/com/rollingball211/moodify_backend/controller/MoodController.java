package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.service.MoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
    private final MoodService moodService;

    public MoodController (MoodService moodService) {
        this.moodService = moodService;
    }
    @GetMapping
    public List<Mood> getAllMoods() {
        return moodService.getAllMoods();
    }

    @PostMapping
    public ResponseEntity<Mood> createMood(@RequestBody Mood mood) {
        Mood saved = moodService.createMood(mood);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
