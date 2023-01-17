package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.coupon.model.GetCouponRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


@Service
public class CouponProvider {

    private final CouponDao couponDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CouponProvider(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    // 쿠폰 id로 쿠폰 조회
    public GetCouponRes getCouponByCouponId(Long couponId) throws BaseException{
        try {
            GetCouponRes getCouponRes = couponDao.getCouponByCouponId(couponId);
            return getCouponRes;
        } catch (Exception exception) {
            logger.error("App - getCouponByUserId Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 쿠폰 전체 조회
    public List<GetCouponRes> getCoupons() throws BaseException {
        try {
            List<GetCouponRes> getCouponResList = couponDao.getCoupons();
            return getCouponResList;
        } catch (Exception exception) {
            logger.error("App - getCoupons Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 회원 id로 쿠폰 조회
    public List<GetCouponRes> getCouponsByUserId(Long userId) throws BaseException {
        // 예외 처리 - 해당 회원 id가 존재하는지
        if (checkCouponByUserId(userId) != 1){
            throw new BaseException(GET_COUPONS_EXISTS_USER_ID);
        }

        try {
            List<GetCouponRes> getCouponResList = couponDao.getCouponsByUserId(userId);
            return getCouponResList;
        } catch (Exception exception) {
            logger.error("App - getCouponsByUserId Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 회원id 존재 체크
    public int checkCouponByUserId(Long userId) throws BaseException {
        try {
            return couponDao.checkCouponByUserId(userId);
        } catch (Exception exception) {
            logger.error("App - checkCouponByUserId provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 쿠폰명 중복 체크
    public int checkCouponName(String couponName) throws BaseException {
        try {
            return couponDao.checkCoupon(couponName);
        } catch (Exception exception) {
            logger.error("App - checkCouponName provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }




}
