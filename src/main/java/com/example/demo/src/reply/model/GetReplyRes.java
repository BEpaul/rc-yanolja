package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReplyRes {
    private Long replyId;
    private Long reviewId;
    private String comment;
    private Long companyId;
}
