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
public class Zzim {
    private Long zzimId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long status;
    private Long userId;
    private Long roomId;
    private Long companyId;
}
