package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.reply.model.GetReplyRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReplyProvider {

    private final ReplyDao replyDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ReplyProvider(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    // 특정 후기 답글 조회
    public List<GetReplyRes> getReplyByReviewId(Long reviewId) throws BaseException {
        try {
            List<GetReplyRes> getReviewResList = replyDao.getReplyByReviewId(reviewId);
            return getReviewResList;
        } catch (Exception exception) {
            logger.error("App - getReplyByReviewId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
