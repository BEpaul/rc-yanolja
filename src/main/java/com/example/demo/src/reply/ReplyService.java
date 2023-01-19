package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.reply.model.PatchReplyReq;
import com.example.demo.src.reply.model.PostReplyReq;
import com.example.demo.src.reply.model.PostReplyRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ReplyService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReplyProvider replyProvider;
    private final ReplyDao replyDao;

    @Autowired
    public ReplyService(ReplyProvider replyProvider, ReplyDao replyDao) {
        this.replyProvider = replyProvider;
        this.replyDao = replyDao;
    }

    // 답글 작성
    public PostReplyRes createReply(PostReplyReq postReplyReq) throws BaseException {
        try {
            Long replyid = replyDao.createReply(postReplyReq);
            return new PostReplyRes(replyid);

        } catch (Exception exception) {
            logger.error("App - createReply service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 답글 수정
    public void modifyReply(PatchReplyReq patchReplyReq) throws BaseException {
        try {
            Long result = replyDao.modifyReply(patchReplyReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_REPLY);
            }
        } catch (Exception exception) {
            logger.error("App - modifyReply service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 답글 삭제
    public Long deleteReply(Long replyId) throws BaseException {
        try {
            Long deleteReplyId = replyDao.deleteReply(replyId);
            return deleteReplyId;
        } catch (Exception exception) {
            logger.error("App - deleteReply service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
