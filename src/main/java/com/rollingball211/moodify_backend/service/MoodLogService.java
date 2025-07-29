package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.domain.User;
import com.rollingball211.moodify_backend.repository.MoodLogRepository;
import com.rollingball211.moodify_backend.repository.MoodRepository;
import com.rollingball211.moodify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MoodLogService {
    private final MoodLogRepository moodLogRepository;
    private final UserRepository userRepository;
    private final MoodRepository moodRepository;

    public MoodLogService(MoodLogRepository moodLogRepository, UserRepository userRepository, MoodRepository moodRepository) {
        this.moodLogRepository = moodLogRepository;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
    }

    //사용자별 Mood 로그 조회 (GET /api/mood-logs/user/{userId})
    //특정 기간 내 로그 조회 (선택사항)

    public MoodLog createMoodLog (Long userId, Long moodId) {
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("user not found" + userId));
        Mood mood = moodRepository.findById(moodId)
                .orElseThrow( () -> new IllegalArgumentException(("mood not found") + moodId));  //orElseThrow()는 Optional 안에 값이 있으면 그 값을 꺼내고,값이 없으면 예외를 던진다.

        MoodLog moodLog = new MoodLog();
        moodLog.setUser(user);
        moodLog.setMood(mood);
        moodLog.setCreatedAt(LocalDateTime.now());

        return moodLogRepository.save(moodLog);


    }

    public List<MoodLog> getAllMoodLogs() {
        return moodLogRepository.findAll();
    }


    //다시보기
    public List<MoodLog> getMoodLogsByUserId(Long userId) {
        return moodLogRepository.findByUserId(userId);
    }
}
