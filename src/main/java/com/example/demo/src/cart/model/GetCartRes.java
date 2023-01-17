package com.example.demo.src.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetCartRes {
    private Long cartId;
    private Long userId;
    private String useDay;
    private String roomName;
    private String roomType;
    private Long normalPrice;
    private Long discountPrice;
    private Long roomId;
    private Long companyId;
}
