package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReviewService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReviewDao reviewDao;
    private final ReviewProvider reviewProvider;

    @Autowired
    public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider) {
        this.reviewDao = reviewDao;
        this.reviewProvider = reviewProvider;
    }

    // 후기 작성
    @Transactional
    public PostReviewRes createReview(PostReviewReq postReviewReq) throws BaseException {

        try {
            long reviewId = reviewDao.createReview(postReviewReq);
            return new PostReviewRes(reviewId);
        } catch (Exception exception) {
            logger.error("App - createReview service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 후기 수정
    @Transactional
    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException {
        try {
            int result = reviewDao.modifyReview(patchReviewReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_REVIEW);
            }
        } catch (Exception exception) {
            logger.error("App - modifyReview service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 후기 삭제
    @Transactional
    public Long deleteReview(Long reviewId) throws BaseException {
        try {
            Long deleteReviewId = reviewDao.deleteReview(reviewId);
            return deleteReviewId;
        } catch (Exception exception) {
            logger.error("App - deleteReview service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
