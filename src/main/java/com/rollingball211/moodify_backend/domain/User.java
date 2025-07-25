package com.rollingball211.moodify_backend.domain;

//entity - DB 테이블과 매핑해줌

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //필드의 값을 자동으로 생성하며, db에서 auto-increment 역할을함
    private Long id;  //JPA에서의 기본 PK,

    private String username;
    private String email;

    public User() {} //기본 생성자를 JPA가 무조건 필요로 함

    public User( String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

