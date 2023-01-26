package com.example.demo.src.company;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.company.model.GetCompanyRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CompanyProvider {

    private final CompanyDao companyDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CompanyProvider(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    // 숙소 전체 조회
    public List<GetCompanyRes> getCompanyAll() throws BaseException {
        try {
            List<GetCompanyRes> getCompanyResList = companyDao.getCompanyAll();
            return getCompanyResList;
        } catch (Exception exception) {
            logger.error("App - getCompanyAll provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 숙소 조회 (카테고리 필터링)
    public List<GetCompanyRes> getCompanyByCategory(String category) throws BaseException {
        try {
            List<GetCompanyRes> getCompanyResList = companyDao.getCompanyByCategory(category);
            return getCompanyResList;
        } catch (Exception exception) {
            logger.error("App - getCompanyByCategory provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 특정 숙소 조회
    public GetCompanyRes getCompanyById(Long companyId) throws BaseException {
        try {
            GetCompanyRes getCompanyRes = companyDao.getCompanyById(companyId);
            return getCompanyRes;
        } catch (Exception exception) {
            logger.error("App - getCompanyById provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
