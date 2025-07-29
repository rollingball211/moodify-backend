package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodService {
    private final MoodRepository moodRepository; //생성자 값 주입 후 불변성 유지 - fianl 키워드

    public MoodService (MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }


    public Mood createMood(Mood mood) {
        return moodRepository.save(mood);
    }

    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }








}
