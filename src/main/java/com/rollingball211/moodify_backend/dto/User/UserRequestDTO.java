package com.rollingball211.moodify_backend.dto.User;

public class UserRequestDTO {
    private String username;
    private String email;

    //보안을 위해서 id는 가리고 dto 이용한다.
    public UserRequestDTO() {}

    public UserRequestDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
