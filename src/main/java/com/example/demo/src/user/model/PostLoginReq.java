package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.Range;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLoginReq {
    @NotBlank
    private String loginId;
    @NotBlank
    @Length(min = 8, max = 20)
    private String password;
}
