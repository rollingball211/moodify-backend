package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.repository.MoodRepository;
import com.rollingball211.moodify_backend.service.MoodMusicMappingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //@GetMapping("/{moodId}")
}


