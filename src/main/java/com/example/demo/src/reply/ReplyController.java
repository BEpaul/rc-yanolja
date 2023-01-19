package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.reply.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/replies")
public class ReplyController {

    private final ReplyProvider replyProvider;
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyProvider replyProvider, ReplyService replyService) {
        this.replyProvider = replyProvider;
        this.replyService = replyService;
    }

    /**
     * 특정 후기의 답글 조회 API
     */
    @Operation(summary = "특정 후기 답글 조회")
    @ResponseBody
    @GetMapping("/{reviewId}")
    public BaseResponse<List<GetReplyRes>> getReplyByReviewId(@PathVariable("reviewId") Long reviewId) {
        try {
            List<GetReplyRes> getReplyResList = replyProvider.getReplyByReviewId(reviewId);
            return new BaseResponse<>(getReplyResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 답글 생성 API
     */
    @Operation(summary = "답글 생성")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReplyRes> createReply(@RequestBody PostReplyReq postReplyReq) {
        try {
            PostReplyRes postReplyRes = replyService.createReply(postReplyReq);
            return new BaseResponse<>(postReplyRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 답글 수정 API
     */
    @Operation(summary = "답글 수정")
    @ResponseBody
    @PatchMapping("/{replyId}")
    public BaseResponse<String> modifyReply(@PathVariable("replyId") Long replyId, @RequestBody Reply reply) {
        try {
            PatchReplyReq patchReplyReq = new PatchReplyReq(replyId, reply.getComment());
            replyService.modifyReply(patchReplyReq);

            String result = "답글 수정 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 답글 삭제 API
     */
    @Operation(summary = "답글 삭제")
    @ResponseBody
    @DeleteMapping("/{replyId}")
    public BaseResponse<Long> deleteReply(@PathVariable("replyId") Long replyId) {
        try {
            Long deleteReplyId = replyService.deleteReply(replyId);
            return new BaseResponse<>(deleteReplyId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
