package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood,Long> {
}
