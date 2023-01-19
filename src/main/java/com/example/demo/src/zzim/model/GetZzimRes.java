package com.example.demo.src.zzim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetZzimRes {
    private Long zzimId;
    private Long userId;
    private Long roomId;
    private Long companyId;
}
