package com.example.demo.src.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCompanyReq {
    @NotBlank
    private String companyCategory;
    @NotBlank
    private String companyName;
    private String event;
    private String service;
    private String policy;
}
