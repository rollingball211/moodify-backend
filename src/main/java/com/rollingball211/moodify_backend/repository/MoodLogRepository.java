package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.MoodLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoodLogRepository extends JpaRepository<MoodLog,Long> {
    //ID 조회하는 메서드 사용하려면 필요함
    List<MoodLog> findByUserId(Long userId);

    //특정 기분에 매핑된 음악 목록을 조회한다
    Optional<MoodLog> findTopByUserId(Long userId);

}
