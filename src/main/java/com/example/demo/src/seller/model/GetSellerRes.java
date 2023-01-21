package com.example.demo.src.seller.model;

import lombok.Getter;

@Getter
public class GetSellerRes {
    private Long sellerId;
    private String representativeName;
    private String businessName;
    private String sellerAddress;
    private String email;
    private String contact;
    private String regiNumber;

    public GetSellerRes(Seller entity) {
        this.sellerId = entity.getSellerId();
        this.representativeName = entity.getRepresentativeName();
        this.businessName = entity.getBusinessName();
        this.sellerAddress = entity.getSellerAddress();
        this.email = entity.getEmail();
        this.contact = entity.getContact();
        this.regiNumber = entity.getRegiNumber();
    }
}
