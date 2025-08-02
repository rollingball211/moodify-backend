package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.Mood;
import com.rollingball211.moodify_backend.domain.MoodMusicMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//MoodMusicMapping 엔티티에 대한 기본 CRUD 제공함!
public interface MoodMusicMappingRepository extends JpaRepository<MoodMusicMapping,Long> {
    //특정 기분(Mood)에 연결된 음악 매핑들 리스트로 조회
    List<MoodMusicMapping> findByMood(Mood mood);
}
