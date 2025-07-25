package com.rollingball211.moodify_backend.service;

import com.rollingball211.moodify_backend.domain.User;
import com.rollingball211.moodify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    //생성자 주입  (초기화)
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //repository(JPARepository)를 상속받기에 method를 구현가능함
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser (Long id) {
        return userRepository.findById(id);
    } //optional?

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser (Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
