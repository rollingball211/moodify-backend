package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.service.MoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
    private final MoodService moodService;

    public MoodController (MoodService moodService) {
        this.moodService = moodService;
    }
    //모든 무드 조회
    @GetMapping
    public List<Mood> getAllMoods() {
        return moodService.getAllMoods();
    }

    //무드 생성
    @PostMapping
    public ResponseEntity<Mood> createMood(@RequestBody Mood mood) {
        Mood created = moodService.createMood(mood);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
}
