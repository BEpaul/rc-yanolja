package com.example.demo.src.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCouponReq {
    private String couponName;
    private Long minPrice;
    private Long discountPrice;
    private Long discountRate;
    private Long discountLimit;
    private String description;
    private String useRegion;
    private String accomType;
    private LocalDateTime startTime;
    private LocalDateTime lostTime;
    private Long userId;
    private Long companyId;
}
