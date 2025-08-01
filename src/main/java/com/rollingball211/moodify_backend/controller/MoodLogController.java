package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.dto.MoodLogRequestDTO;
import com.rollingball211.moodify_backend.dto.MoodLogResponseDTO;
import com.rollingball211.moodify_backend.service.MoodLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mood-logs")
public class MoodLogController {
    private final MoodLogService moodLogService;

    public MoodLogController (MoodLogService moodLogService) {
        this.moodLogService = moodLogService;
    }

    @PostMapping
    public ResponseEntity<MoodLogResponseDTO> createMoodLog(@RequestBody MoodLogRequestDTO requestDTO) {
        MoodLog created = moodLogService.createMoodLog(requestDTO.getUserId(),requestDTO.getMoodId());

        MoodLogResponseDTO responseDTO = new MoodLogResponseDTO(
                created.getId(),
                created.getUser().getUsername(),
                created.getMood().getName(),
                created.getCreatedAt()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    //밑에 다시보기 - 이해가 잘 안감
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MoodLogResponseDTO>> getMoodLogsByUser(@PathVariable Long userId) {
        List<MoodLog> logs = moodLogService.getMoodLogsByUserId(userId);

        List<MoodLogResponseDTO> responseDTOs = logs.stream()
                .map(log -> new MoodLogResponseDTO(
                        log.getId(),
                        log.getUser().getUsername(),
                        log.getMood().getName(),
                        log.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping
    public ResponseEntity<List<MoodLogResponseDTO>> getAllMoodLogs() {
        List<MoodLog> logs = moodLogService.getAllMoodLogs();

        List<MoodLogResponseDTO> responseDTOs = logs.stream()
                .map(log -> new MoodLogResponseDTO(
                        log.getId(),
                        log.getUser().getUsername(),
                        log.getMood().getName(),
                        log.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

}
