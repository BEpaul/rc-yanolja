package com.example.demo.src.coupon;

import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.src.coupon.model.PostCouponReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CouponDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 쿠폰 전체 조회 jdbc
    public List<GetCouponRes> getCoupons() {
        String getCouponsQuery = "select * from coupon";
        return this.jdbcTemplate.query(getCouponsQuery,
                (rs, rowNum) -> new GetCouponRes(
                        rs.getLong("coupon_id"),
                        rs.getLong("user_id"),
                        rs.getLong("company_id"),
                        rs.getString("coupon_name"),
                        rs.getLong("minimum_price"),
                        rs.getLong("discount_price"),
                        rs.getLong("discount_rate"),
                        rs.getLong("discount_limit"),
                        rs.getString("description"),
                        rs.getString("use_region"),
                        rs.getString("accom_type"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("lost_time").toLocalDateTime())
                );
    }


    // 회원 id로 쿠폰 조회 jdbc
    public List<GetCouponRes> getCouponsByUserId(Long userId) {
        String getCouponsByUserIdQuery = "select * from coupon where user_id = ?";
        Long getCouponsByUserParams = userId;
        return this.jdbcTemplate.query(getCouponsByUserIdQuery,
                (rs, rowNum) -> new GetCouponRes(
                        rs.getLong("coupon_id"),
                        rs.getLong("user_id"),
                        rs.getLong("company_id"),
                        rs.getString("coupon_name"),
                        rs.getLong("minimum_price"),
                        rs.getLong("discount_price"),
                        rs.getLong("discount_rate"),
                        rs.getLong("discount_limit"),
                        rs.getString("description"),
                        rs.getString("use_region"),
                        rs.getString("accom_type"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("lost_time").toLocalDateTime()),
                getCouponsByUserParams
                );
    }


    // 쿠폰 id로 쿠폰 조회 jdbc
    public GetCouponRes getCouponByCouponId(Long couponId) {
        String getCouponByCouponIdQuery = "select * from coupon where coupon_id = ?";
        Long getCouponByCouponIdParams = couponId;

        return this.jdbcTemplate.queryForObject(getCouponByCouponIdQuery,
                (rs, rowNum) -> new GetCouponRes(
                        rs.getLong("coupon_id"),
                        rs.getLong("user_id"),
                        rs.getLong("company_id"),
                        rs.getString("coupon_name"),
                        rs.getLong("minimum_price"),
                        rs.getLong("discount_price"),
                        rs.getLong("discount_rate"),
                        rs.getLong("discount_limit"),
                        rs.getString("description"),
                        rs.getString("use_region"),
                        rs.getString("accom_type"),
                        rs.getTimestamp("start_time").toLocalDateTime(),
                        rs.getTimestamp("lost_time").toLocalDateTime()),
                getCouponByCouponIdParams);
    }

    // 쿠폰 생성 jdbc
    public int createCoupon(PostCouponReq postCouponReq) {
        String createCouponQuery = "insert into coupon (coupon_name, minimum_price, discount_price, discount_rate, discount_limit, description, use_region, accom_type, start_time, lost_time, user_id, company_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createCouponParams = new Object[]{postCouponReq.getCouponName(), postCouponReq.getMinPrice(),
                postCouponReq.getDiscountPrice(), postCouponReq.getDiscountRate(), postCouponReq.getDiscountLimit(),
                postCouponReq.getDescription(), postCouponReq.getUseRegion(), postCouponReq.getAccomType(),
                postCouponReq.getStartTime(), postCouponReq.getLostTime(), postCouponReq.getUserId(), postCouponReq.getCompanyId()};

        this.jdbcTemplate.update(createCouponQuery, createCouponParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public int checkCouponByUserId(Long userId) {
        String checkCouponByUserIdQuery = "select exists (select user_id from coupon where user_id = ?)";
        Long checkCouponByUserIdParams = userId;
        return this.jdbcTemplate.queryForObject(checkCouponByUserIdQuery, int.class, checkCouponByUserIdParams);
    }



    // 중복 체크 jdbc
    public int checkCoupon(String couponName) {
        String checkCouponQuery = "select exists (select coupon_name from coupon where coupon_name = ?)";
        String checkCouponParams = couponName;

        return this.jdbcTemplate.queryForObject(checkCouponQuery, int.class, checkCouponParams);
    }

}
