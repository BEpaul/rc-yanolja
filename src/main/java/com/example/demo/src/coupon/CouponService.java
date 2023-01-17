package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.src.coupon.model.PostCouponRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CouponService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CouponDao couponDao;
    private final CouponProvider couponProvider;

    @Autowired
    public CouponService(CouponDao couponDao, CouponProvider couponProvider) {
        this.couponDao = couponDao;
        this.couponProvider = couponProvider;
    }

//    public Optional<PostCouponRes> postCouponRes // 일단 해보고 옵셔널 써보자
//    public Optional<PostCouponRes> createCoupon(PostCouponReq postCouponReq) throws BaseException {
//        // 중복 체크
//        if (couponProvider.checkCouponName(postCouponReq.getCouponName()) == 1) {
//            throw new BaseException(POST_COUPONS_EXISTS_NAME);
//        }

//        try {
//            long couponId = couponDao.createCoupon(postCouponReq);
//            return Optional.ofNullable(new PostCouponRes(couponId));
//        } catch (Exception exception) {
//            logger.error("App - createCoupon Service Error", exception);
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
    public PostCouponRes createCoupon(PostCouponReq postCouponReq) throws BaseException {
        // 중복 체크
        if (couponProvider.checkCouponName(postCouponReq.getCouponName()) == 1) {
            throw new BaseException(POST_COUPONS_EXISTS_NAME);
        }

        try {
            long couponId = couponDao.createCoupon(postCouponReq);
            return new PostCouponRes(couponId);
        } catch (Exception exception) {
            logger.error("App - createCoupon Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
