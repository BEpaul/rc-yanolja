package com.example.demo.src.company;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.company.model.Company;
import com.example.demo.src.company.model.GetCompanyRes;
import com.example.demo.src.company.model.PostCompanyReq;
import com.example.demo.src.company.model.PostCompanyRes;
import com.example.demo.src.company.model.DeleteCompanyReq;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app/company")
public class CompanyController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CompanyProvider companyProvider;
    private final CompanyService companyService;
    private final CompanyDao companyDao;

    @Autowired
    public CompanyController(CompanyProvider companyProvider, CompanyService companyService, CompanyDao companyDao) {
        this.companyProvider = companyProvider;
        this.companyService = companyService;
        this.companyDao = companyDao;
    }

    /**
     * 숙소 전체 조회 API
     */
//    @Operation(summary = "숙소 전체 조회")
//    @ResponseBody
//    @GetMapping("")
//    public BaseResponse<List<GetCompanyRes>> getCompanyAll(@Valid @RequestParam(required = false) String category) {
//        if (category == null) {
//            List<GetCompanyRes> getCompanyResList = companyProvider.getCompanyAll();
//            return new BaseResponse<>(getCompanyResList);
//        }
//
//        List<GetCompanyRes> getCompanyResList = companyProvider.getCompanyByCategory(category);
//        return new BaseResponse<>(getCompanyResList);
//        }


    /**
     * 특정 숙소 조회 API
     */
    @Operation(summary = "특정 숙소 조회")
    @ResponseBody
    @GetMapping("/{companyId}")
    public BaseResponse<GetCompanyRes> getCompany(@Valid @PathVariable("companyId") Long companyId) {
        GetCompanyRes getCompanyRes = companyProvider.getCompanyById(companyId);
        return new BaseResponse<>(getCompanyRes);
    }


    /**
     * 숙소 등록 API
     */
    @Operation(summary = "숙소 등록")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostCompanyRes> createCompany(@Valid @RequestBody PostCompanyReq postCompanyReq) {
        PostCompanyRes postCompanyRes = companyService.createCompany(postCompanyReq);

        return new BaseResponse<>(postCompanyRes);
    }


    /**
     * 숙소 삭제 API
     */
    @Operation(summary = "숙소 삭제")
    @ResponseBody
    @DeleteMapping("/{companyId}")
    public BaseResponse<String> deleteCompany(@Valid @PathVariable("companyId") Long companyId, @RequestBody Company company) {
        DeleteCompanyReq deleteCompanyReq = new DeleteCompanyReq(companyId, company.getStatus());
        companyService.modifyCompanyStatus(deleteCompanyReq);

        String result = "숙소 삭제 완료";
        return new BaseResponse<>(result);
    }
}
