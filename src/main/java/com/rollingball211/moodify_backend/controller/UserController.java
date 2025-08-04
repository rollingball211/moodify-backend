package com.rollingball211.moodify_backend.controller;

import com.rollingball211.moodify_backend.domain.User;
import com.rollingball211.moodify_backend.dto.User.UserRequestDTO;
import com.rollingball211.moodify_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name= "user-controller", description = "사용자 관련 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //모든 유저정보 조회
    @Operation(summary = "모든 사용자 조회")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //user Create
    @Operation(summary = "사용자 등록")
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User created = userService.createUser(userRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    //id로 User 찾기
    @Operation(summary = "사용자 찾기")
    @GetMapping("findById/{id}")
    public ResponseEntity<User> getUser(@Parameter(description = "사용자 ID") @PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    //User 정보 변경
    @Operation(summary = "사용자 정보 수정")
    @PutMapping("modify/{id}")
    public ResponseEntity<User> updateUser(@Parameter (description = "사용자ID") @PathVariable Long id, @RequestBody User user) {
        try {
            User updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //User 삭제
    @Operation(summary = "사용자 삭제")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter (description = "사용자ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}