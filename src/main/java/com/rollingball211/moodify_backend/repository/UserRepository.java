package com.rollingball211.moodify_backend.repository;

import com.rollingball211.moodify_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //기본 CRUD 메서드 제공 (SAVE/FINDBYID/FINDALL/DELETE)

}
