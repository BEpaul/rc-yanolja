package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
    private Long reviewId;
    private String comment;
    private String imageUrl;
    private Long isReply;
    private Long userId;
    private Long roomId;
    private Long companyId;
}
