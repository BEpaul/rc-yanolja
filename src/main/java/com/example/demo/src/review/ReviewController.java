package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/reviews")
public class ReviewController {

    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewProvider reviewProvider, ReviewService reviewService) {
        this.reviewProvider = reviewProvider;
        this.reviewService = reviewService;
    }

    /**
     * 특정 회원 후기 조회 API
     */
    @Operation(summary = "특정 회원 후기 조회")
    @ResponseBody
    @GetMapping("/user-id/{userId}")
    public BaseResponse<List<GetReviewRes>> getReviewByUserId(@PathVariable("userId") Long userId) {
        try {
            List<GetReviewRes> getReviewResList = reviewProvider.getReviewByUserId(userId);
            return new BaseResponse<>(getReviewResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 특정 숙소 후기 조회 API
     */
    @Operation(summary = "특정 숙소 후기 조회")
    @ResponseBody
    @GetMapping("/company-id/{companyId}")
    public BaseResponse<List<GetReviewRes>> getReviewByCompanyId(@PathVariable("companyId") Long companyId) {
        try {
            List<GetReviewRes> getReviewResList = reviewProvider.getReviewByCompanyId(companyId);
            return new BaseResponse<>(getReviewResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 후기 작성 API
     */
    @Operation(summary = "후기 작성")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        try {
            PostReviewRes postReviewRes = reviewService.createReview(postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 후기 수정 API
     */
    @Operation(summary = "후기 수정")
    @ResponseBody
    @PatchMapping("/{reviewId}")
    public BaseResponse<String> modifyReview(@PathVariable("reviewId") Long reviewId, @RequestBody Review review) {
        try {
            PatchReviewReq patchReviewReq = new PatchReviewReq(reviewId, review.getComment(), review.getImageUrl());
            reviewService.modifyReview(patchReviewReq);

            String result = "후기 수정 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 후기 삭제 API
     */
    @Operation(summary = "후기 삭제")
    @ResponseBody
    @DeleteMapping("/{reviewId}")
    public BaseResponse<Long> deleteReview(@PathVariable("reviewId") Long reviewId) {
        try {
            Long deleteReviewId = reviewService.deleteReview(reviewId);
            return new BaseResponse<>(deleteReviewId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
