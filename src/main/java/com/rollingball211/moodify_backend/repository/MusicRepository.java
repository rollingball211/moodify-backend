package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music,Long> {
}
