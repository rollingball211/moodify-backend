package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.dto.mood.MoodResponseDTO;
import com.rollingball211.moodify_backend.service.MoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "mood-log-controller", description = "moodLog 컨트롤러")
@RestController
@RequestMapping("/api/moods")
public class MoodController {
    private final MoodService moodService;

    public MoodController (MoodService moodService) {
        this.moodService = moodService;
    }

    //모든 무드 조회
    //DTO 버전으로 조회 0810
    @Operation(summary = "모든 무드를 조회한다!")
    @GetMapping
    public ResponseEntity<List<MoodResponseDTO>> getAllMoods() {
        List<MoodResponseDTO> moods = moodService.getAllMoods();
        return ResponseEntity.ok(moods);
    }

    /** 이전 버전
     public List<Mood> getAllMoods() {
        return moodService.getAllMoods();
    }
    **/


    //무드 생성
    /**
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
    **/
}
