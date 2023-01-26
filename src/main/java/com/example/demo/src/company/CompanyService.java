package com.example.demo.src.company;

import com.example.demo.config.BaseException;
import com.example.demo.src.company.model.PostCompanyReq;
import com.example.demo.src.company.model.PostCompanyRes;
import com.example.demo.src.company.model.DeleteCompanyReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CompanyService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CompanyProvider companyProvider;
    private final CompanyDao companyDao;

    @Autowired
    public CompanyService(CompanyProvider companyProvider, CompanyDao companyDao) {
        this.companyProvider = companyProvider;
        this.companyDao = companyDao;
    }


    // 숙소 생성
    @Transactional
    public PostCompanyRes createCompany(PostCompanyReq postCompanyReq) throws BaseException {
        try {
            Long companyId = companyDao.createCompany(postCompanyReq);
            return new PostCompanyRes(companyId);
        } catch (Exception exception) {
            logger.error("App - createCompany sevice error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 숙소 정보 변경

    // 숙소 삭제
    @Transactional
    public void modifyCompanyStatus(DeleteCompanyReq deleteCompanyReq) throws BaseException {
        try {
            Long result = companyDao.modifyCompanyStatus(deleteCompanyReq);
            if (result == 0) {
                throw new BaseException(MODIFY_FAIL_COMPANY_STATUS);
            }
        } catch (Exception exception) {
            logger.error("App - modifyCompanyStatus service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
