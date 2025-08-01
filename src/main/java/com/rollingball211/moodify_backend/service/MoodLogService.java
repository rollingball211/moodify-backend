package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.domain.User;
import com.rollingball211.moodify_backend.dto.MoodLogResponseDTO;
import com.rollingball211.moodify_backend.repository.MoodLogRepository;
import com.rollingball211.moodify_backend.repository.MoodRepository;
import com.rollingball211.moodify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        MoodLog saved  = moodLogRepository.save(moodLog);
        return moodLogRepository.save(saved);


    }

    public List<MoodLog> getAllMoodLogs() {
        return moodLogRepository.findAll();
    }


    //id별 MoodLog 가져오기
    //모든 moodLog를 가져와야 하므로 stream.map을 사용해야함
    //findById는 하나만 찾아오기 때문.
    //보안을 위해 DTO형태로 만들어서 API에 사용할수 있게 보냄.
    public List<MoodLog> getMoodLogsByUserId(Long userId) {
        List<MoodLog> logs = moodLogRepository.findByUserId(userId);
        return logs.stream()
                .map(MoodLogResponseDTO::new)
                .collect(Collectors.toList());
    }
}
