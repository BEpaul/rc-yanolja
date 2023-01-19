package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private Long replyId;
    private Long reviewId;
    private String comment;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long status;
    private Long companyId;
}
