package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.service.MoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
    private final MoodService moodService;
}
