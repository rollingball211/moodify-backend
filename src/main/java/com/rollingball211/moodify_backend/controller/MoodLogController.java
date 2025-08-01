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

        MoodLogResponseDTO responseDTO = new MoodLogResponseDTO(created);

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
        List<MoodLogResponseDTO> responseDTOs = moodLogService.getMoodLogsByUserId(userId);
        return ResponseEntity.ok(responseDTOs);
    }


    //전체 로그 들고오기
    @GetMapping
    public ResponseEntity<List<MoodLogResponseDTO>> getAllMoodLogs() {
        List<MoodLogResponseDTO> responseDTOs = moodLogService.getAllMoodLogs();
        return ResponseEntity.ok(responseDTOs);
    }

}
