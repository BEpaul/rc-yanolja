package com.example.demo.src.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReplyReq {
    private Long reviewId;
    private String comment;
    private Long companyId;
}
