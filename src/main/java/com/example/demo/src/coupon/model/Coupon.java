package com.example.demo.src.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private Long couponId;
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
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long status;
    private Long userId;
    private Long companyId;
}
