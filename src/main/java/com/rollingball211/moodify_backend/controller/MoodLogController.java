package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.MoodLog;
import com.rollingball211.moodify_backend.dto.moodLog.MoodLogRequestDTO;
import com.rollingball211.moodify_backend.dto.moodLog.MoodLogResponseDTO;
import com.rollingball211.moodify_backend.service.MoodLogService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mood-logs")
public class MoodLogController {
    private final MoodLogService moodLogService;

    public MoodLogController (MoodLogService moodLogService) {
        this.moodLogService = moodLogService;
    }

    //아이디에서 moodLog 생성
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

    //아이디별 분위기 로그 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MoodLogResponseDTO>> getMoodLogsByUser(@Parameter(description = "유저ID")  @PathVariable Long userId) {
        List<MoodLogResponseDTO> responseDTOs = moodLogService.getMoodLogsByUserId(userId);
        return ResponseEntity.ok(responseDTOs);
    }


    //전체 로그 들고오기
    @GetMapping("getAllMoodLogs")
    public ResponseEntity<List<MoodLogResponseDTO>> getAllMoodLogs() {
        List<MoodLogResponseDTO> responseDTOs = moodLogService.getAllMoodLogs();
        return ResponseEntity.ok(responseDTOs);
    }

}
