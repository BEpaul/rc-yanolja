package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.GetReviewRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReviewProvider {
    private final ReviewDao reviewDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ReviewProvider(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    // 특정 회원 후기 조회
    public List<GetReviewRes> getReviewByUserId(Long userId) throws BaseException {
        // 예외 처리 - 해당 회원 존재 파악
        if (checkIsUser(userId) != 1) {
            throw new BaseException(GET_REVIEWS_NOT_EXISTS);
        }

        try {
            List<GetReviewRes> getReviewResList = reviewDao.getReviewByUserId(userId);
            return getReviewResList;
        } catch (Exception exception) {
            logger.error("App - getReviewByUserId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 숙소 후기 조회
    public List<GetReviewRes> getReviewByCompanyId(Long companyId) throws BaseException {
        try {
            List<GetReviewRes> getReviewResList = reviewDao.getReviewByCompanyId(companyId);
            return getReviewResList;
        } catch (Exception exception) {
            logger.error("App - getReviewByCompanyId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Long checkIsUser(Long userId) throws BaseException {
        try {
            return reviewDao.checkIsUser(userId);
        } catch (Exception exception) {
            logger.error("App checkIsUser provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
