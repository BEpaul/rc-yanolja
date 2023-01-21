package com.example.demo.src.seller;

import com.example.demo.src.seller.model.GetSellerRes;
import com.example.demo.src.seller.model.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SellerService {

    private final SellerRepository sellerRepository;


    public GetSellerRes findByid(Long companyId) {
        Seller entity = sellerRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("해당 회사 정보가 없습니다."));

        return new GetSellerRes(entity);
    }
}
