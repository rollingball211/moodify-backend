package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.domain.User;
import com.rollingball211.moodify_backend.dto.moodLog.MoodLogResponseDTO;
import com.rollingball211.moodify_backend.repository.MoodLogRepository;
import com.rollingball211.moodify_backend.repository.MoodRepository;
import com.rollingball211.moodify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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


    /** 사용자별 MoodLog 조회하기 - DTO 리스트를 반환한다.
     * 모든 moodLog를 가져와야 하므로 stream.map을 사용해야함
     * findById는 하나만 찾아오기 때문.
     * 보안을 위해 DTO형태로 만들어서 API에 사용할수 있게 보냄.
   **/

    public List<MoodLogResponseDTO> getMoodLogsByUserId(Long userId) {
        List<MoodLog> logs = moodLogRepository.findByUserId(userId);
        return logs.stream()
                .map(MoodLogResponseDTO::new)
                .collect(Collectors.toList());
    }

    //MoodLog 생성하는 method
    public MoodLog createMoodLog(Long userId, Long moodId) {
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException(("user not found!!!" + userId)));
        Mood mood = moodRepository.findById(moodId)
                .orElseThrow(() -> new IllegalArgumentException("mood not found: " + moodId));

        MoodLog moodLog = new MoodLog();
        moodLog.setUser(user);
        moodLog.setMood(mood);
        moodLog.setCreatedAt(LocalDateTime.now());

        return moodLogRepository.save(moodLog);
    }

    //전체 MoodLog 조회하기 - DTO 리스트를 반환시킨다.
    public List<MoodLogResponseDTO> getAllMoodLogs() {
        List<MoodLog> logs =  moodLogRepository.findAll();
        return logs.stream()
                .map(MoodLogResponseDTO::new)
                .collect(Collectors.toList());
    }






}
