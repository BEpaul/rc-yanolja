package com.example.demo.src.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCompanyRes {
    private Long companyId;
    private String companyCategory;
    private String companyName;
    private String event;
    private String service;
    private String policy;
}
