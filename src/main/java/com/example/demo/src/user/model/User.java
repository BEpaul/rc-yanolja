package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userIdx;
    private String ID;
    private String userName;
    private String password;
    private String email;
//    private Long userId;
//    private String loginId;
//    private String email;
//    private String password;
//    private String phoneNumber;
//    private String nickname;
//    private Long point;
//    private LocalDateTime createdTime;
//    private LocalDateTime updatedTime;
//    private Long status;
}
