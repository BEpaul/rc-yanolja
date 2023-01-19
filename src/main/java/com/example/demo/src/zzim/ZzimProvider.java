package com.example.demo.src.zzim;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.zzim.model.GetZzimRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ZzimProvider {

    private final ZzimDao zzimDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ZzimProvider(ZzimDao zzimDao) {
        this.zzimDao = zzimDao;
    }

    // 특정 회원 찜 목록 조회
    public List<GetZzimRes> getZzimByUserId(Long userId) throws BaseException {
        try {
            List<GetZzimRes> getZzimResList = zzimDao.getZzimByUserId(userId);
            return getZzimResList;
        } catch (Exception exception) {
            logger.error("App - getZzimByUserId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
