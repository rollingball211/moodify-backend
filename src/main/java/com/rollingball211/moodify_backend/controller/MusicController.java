package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.Music;
import com.rollingball211.moodify_backend.dto.music.MusicRequestDTO;
import com.rollingball211.moodify_backend.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name ="musicController", description = "음악 관련 API")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    //생성자 주입하기!

    public MusicController (MusicService musicService) {
        this.musicService = musicService;
    }

    /**201 성공
     * POST 요청으로 새 리소스가 생성되면
     * 서버는 201 Created 상태 코드를 반환하는 게 표준 권장사항!
     * **/
    @Operation(summary = "음악 등록")
    @PostMapping("applyMusic/{id}")
    public ResponseEntity<Music> createMusic (@RequestBody MusicRequestDTO musicRequestDTO) {
        Music created = musicService.createMusic(musicRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()  //현재 요청 URL 기반으로 URI 생성
                .path("/{id}") //안의 값으로 추가하겠다.
                .buildAndExpand(created.getId()) // 실제 생성된 ID값으로 채우겠다.
                .toUri();// 완성문자열을 객체로 바꿔줌.
        return ResponseEntity.created(location).body(created);
    }
    @Operation(summary = "모든 음악 목록 조회")
    @GetMapping("getAllMusic")
    public List<Music> getAllMusic() {
        return musicService.getAllMusic();
    }

    @Operation(summary = "음악 단건 조회(ID)")
    @GetMapping("findMusicById/{id}")
    public ResponseEntity<Music> getMusicById(@Parameter(description = "음악ID") @PathVariable Long id) {
        return musicService.getMusicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }


}
