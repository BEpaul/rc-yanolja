package com.example.demo.src.reply;

import com.example.demo.src.reply.model.GetReplyRes;
import com.example.demo.src.reply.model.PatchReplyReq;
import com.example.demo.src.reply.model.PostReplyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 특정 후기 답글 조회 jdbc
    public List<GetReplyRes> getReplyByReviewId(Long reviewId) {
        String getReplyByReviewIdQuery = "select * from reply where review_id = ?";
        Long getReplyByReviewIdParams = reviewId;

        return this.jdbcTemplate.query(getReplyByReviewIdQuery,
                (rs, rowNum) -> new GetReplyRes(
                        rs.getLong("reply_id"),
                        rs.getLong("review_id"),
                        rs.getString("comment"),
                        rs.getLong("company_id")),
                getReplyByReviewIdParams
        );
    }

    // 답글 작성 jdbc
    public Long createReply(PostReplyReq postReplyReq) {
        String createReplyQuery = "insert into reply (review_id, comment, company_id) VALUES (?,?,?)";
        Object[] createReplyParams = new Object[]{postReplyReq.getReviewId(), postReplyReq.getComment(), postReplyReq.getCompanyId()};

        this.jdbcTemplate.update(createReplyQuery, createReplyParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }

    // 답글 수정 jdbc
    public Long modifyReply(PatchReplyReq patchReplyReq) {
        String modifyReplyQuery = "update reply set comment = ? where reply_id = ?";
        Object[] modifyReplyParams = new Object[]{patchReplyReq.getComment(), patchReplyReq.getReplyId()};

        return Long.valueOf(this.jdbcTemplate.update(modifyReplyQuery, modifyReplyParams));
    }

    // 답글 삭제 jdbc
    public Long deleteReply(Long replyId) {
        String deleteReplyQuery = "delete from reply where reply_id = ?";
        Long deleteReplyParams = replyId;

        this.jdbcTemplate.update(deleteReplyQuery, deleteReplyParams);

        return replyId;
    }


}
