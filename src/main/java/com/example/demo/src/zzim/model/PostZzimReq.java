package com.example.demo.src.zzim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostZzimReq {
    private Long userId;
    private Long roomId;
    private Long companyId;
}
