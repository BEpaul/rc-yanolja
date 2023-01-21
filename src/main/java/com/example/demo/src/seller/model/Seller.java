package com.example.demo.src.seller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Seller {
    @Column(name = "sellerinfo_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @Column(name = "representative_name")
    private String representativeName;
    @Column(name = "business_name")
    private String businessName;

    @Column(name = "seller_address")
    private String sellerAddress;
    private String email;
    private String contact;
    @Column(name = "regi_number")
    private String regiNumber;
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    private Long status;
    @Column(name = "company_id")
    private Long companyId;


    @Builder
    public Seller(Long sellerId, String representativeName, String businessName, String sellerAddress,
                  String email, String contact, String regiNumber, LocalDateTime createdTime,
                  LocalDateTime updatedTime, Long status, Long companyId) {
        this.sellerId = sellerId;
        this.representativeName = representativeName;
        this.businessName = businessName;
        this.sellerAddress = sellerAddress;
        this.email = email;
        this.contact = contact;
        this.regiNumber = regiNumber;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.status = status;
        this.companyId = companyId;
    }


}
