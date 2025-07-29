package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.MoodLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodLogRepository extends JpaRepository<MoodLog,Long> {
    //다시보기
    List<MoodLog> findByUserId(Long userId);
}
