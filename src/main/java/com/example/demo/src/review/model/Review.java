package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewId;
    private String comment;
    private String imageUrl;
    private Long isReply;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long status;
    private Long userId;
    private Long roomId;
    private Long companyId;
}
