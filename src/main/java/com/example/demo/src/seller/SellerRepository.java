package com.example.demo.src.seller;

import com.example.demo.src.seller.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
