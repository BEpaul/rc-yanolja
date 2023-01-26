package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    @NotBlank
    private String loginId;
    @NotBlank
    @Length(min = 6, max = 20)
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    @Length(min = 8, max = 20)
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Length(min = 4, max = 12)
    private String nickname;
}
