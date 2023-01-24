package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
    private Long userId;
    private String loginId;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
    private Long point;

}
