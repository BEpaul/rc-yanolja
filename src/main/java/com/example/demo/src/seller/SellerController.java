package com.example.demo.src.seller;

import com.example.demo.src.seller.model.GetSellerRes;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/seller-info")
public class SellerController {
    private final SellerService sellerService;

    @Operation(summary = "숙소의 판매자 정보 조회")
    @GetMapping("/{companyId}")
    public GetSellerRes findById(@PathVariable("companyId") Long companyId) {
        return sellerService.findByid(companyId);
    }
}
