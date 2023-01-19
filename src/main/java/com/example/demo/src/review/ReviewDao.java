package com.example.demo.src.review;

import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 특정 회원 후기 조회 jdbc
    public List<GetReviewRes> getReviewByUserId(Long userId) {
        String getReviewByUserIdQuery = "select * from review where user_id = ?";
        Long getReviewByUserIdParams = userId;
        return this.jdbcTemplate.query(getReviewByUserIdQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getLong("review_id"),
                        rs.getString("comment"),
                        rs.getString("image_url"),
                        rs.getLong("is_reply"),
                        rs.getLong("user_id"),
                        rs.getLong("room_id"),
                        rs.getLong("company_id")),
                getReviewByUserIdParams
        );
    }

    // 특정 숙소 후기 조회 jdbc
    public List<GetReviewRes> getReviewByCompanyId(Long companyId) {
        String getReviewByCompanyIdQuery = "select * from review where company_id = ?";
        Long getReviewByCompanyIdParams = companyId;
        return this.jdbcTemplate.query(getReviewByCompanyIdQuery,
                (rs, rowNum) -> new GetReviewRes(
                        rs.getLong("review_id"),
                        rs.getString("comment"),
                        rs.getString("image_url"),
                        rs.getLong("is_reply"),
                        rs.getLong("user_id"),
                        rs.getLong("room_id"),
                        rs.getLong("company_id")),
                getReviewByCompanyIdParams
                );
    }

    // 후기 작성 jdbc
    public Long createReview(PostReviewReq postReviewReq) {
        String createReviewQuery = "insert into review (comment, image_url, is_reply, user_id, room_id, company_id) VALUES (?,?,?,?,?,?)";
        Object[] createReviewParams = new Object[]{postReviewReq.getComment(), postReviewReq.getImageUrl(),
        postReviewReq.getIsReply(), postReviewReq.getUserId(), postReviewReq.getRoomId(), postReviewReq.getCompanyId()};

        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }

    // 후기 수정 jdbc
    public int modifyReview(PatchReviewReq patchReviewReq) {
        String modifyReviewQuery = "update review set comment = ?, image_url = ? where review_id = ?";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getComment(), patchReviewReq.getImageUrl(), patchReviewReq.getReviewId()};

        return this.jdbcTemplate.update(modifyReviewQuery, modifyReviewParams);
    }

    // 후기 삭제 jdbc
    public Long deleteReview(Long reviewId) {
        String deleteReviewQuery = "delete from review where review_id = ?";
        Long deleteReviewParams = reviewId;

        this.jdbcTemplate.update(deleteReviewQuery, deleteReviewParams);

        return reviewId;
    }


    // 예외 처리 - 회원 존재 유무
    public Long checkIsUser(Long userId) {
        String checkIsUserQuery = "select exists (select review_id from review where user_id = ?)";
        Long checkIsUserParams = userId;

        return this.jdbcTemplate.queryForObject(checkIsUserQuery, Long.class, checkIsUserParams);
    }
}
