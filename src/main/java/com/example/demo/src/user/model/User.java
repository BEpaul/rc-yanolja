package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String loginId;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
    private Long point;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long status;
}
