package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.coupon.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.POST_COUPONS_EMPTY_NAME;

@RestController
@RequestMapping("/app/coupons")
public class CouponController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CouponProvider couponProvider;

    @Autowired
    private final CouponService couponService;

    public CouponController(CouponProvider couponProvider, CouponService couponService) {
        this.couponProvider = couponProvider;
        this.couponService = couponService;
    }

    /**
     * 쿠폰 조회 API (쿠폰 id)
     */
    @Operation(summary = "특정 쿠폰 조회")
    @ResponseBody
    @GetMapping("/{couponId}")
    public BaseResponse<GetCouponRes> getCouponByCouponId(@PathVariable("couponId") Long couponId) {
        try {
            GetCouponRes getCouponRes = couponProvider.getCouponByCouponId(couponId);
            return new BaseResponse<>(getCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 전체 조회 API or 회원 소유 쿠폰 조회 API
     */
    @Operation(summary = "쿠폰 전체 조회 또는 회원이 소유한 쿠폰 조회")
    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetCouponRes>> getCoupons(@RequestParam(required = false) Long userId) {
        try {
            if (userId == null) {
                List<GetCouponRes> getCouponResList = couponProvider.getCoupons();
                return new BaseResponse<>(getCouponResList);
            } else {
                List<GetCouponRes> getCouponResList = couponProvider.getCouponsByUserId(userId);
                return new BaseResponse<>(getCouponResList);
            }
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 쿠폰 생성 API
     */
    @Operation(summary = "쿠폰 생성")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostCouponRes> createCoupon(@RequestBody PostCouponReq postCouponReq) {
        // 예외 처리
        if (postCouponReq.getCouponName() == null) {
            return new BaseResponse<>(POST_COUPONS_EMPTY_NAME);
        }

//        Optional<PostCouponRes> postCouponRes = couponService.createCoupon(postCouponReq);
//        PostCouponRes postCouponRes1 = postCouponRes.orElseThrow(() -> {
//            throw new BaseException();
//        });
//        return BaseResponse<postCouponRes1>;
//            return new BaseResponse<>(postCouponRes);

        try {
            PostCouponRes postCouponRes = couponService.createCoupon(postCouponReq);
            return new BaseResponse<>(postCouponRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 사용 API
     */
    @Operation(summary = "쿠폰 사용")
    @ResponseBody
    @PatchMapping("/{couponId}")
    public BaseResponse<String> useUpCoupon(@PathVariable("couponId") Long couponId, @RequestBody Coupon coupon) {
        try {
            PatchCouponReq patchCouponReq = new PatchCouponReq(couponId, coupon.getStatus());
            couponService.modifyCouponStatus(patchCouponReq);

            String result = "쿠폰 사용 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }



}
