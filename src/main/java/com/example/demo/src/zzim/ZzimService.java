package com.example.demo.src.zzim;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.zzim.model.PostZzimReq;
import com.example.demo.src.zzim.model.PostZzimRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class ZzimService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ZzimDao zzimDao;
    private final ZzimProvider zzimProvider;

    @Autowired
    public ZzimService(ZzimDao zzimDao, ZzimProvider zzimProvider) {
        this.zzimDao = zzimDao;
        this.zzimProvider = zzimProvider;
    }

    // 찜하기
    public PostZzimRes createZzim(PostZzimReq postZzimReq) throws BaseException {
        try {
            Long zzimId = zzimDao.createZzim(postZzimReq);
            return new PostZzimRes(zzimId);
        } catch (Exception exception) {
            logger.error("App - createZzim service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 찜 목록에서 삭제
    public Long deleteZzim(Long zzimId) throws BaseException {
        try {
            Long deleteZzimId = zzimDao.deleteZzim(zzimId);
            return deleteZzimId;
        } catch (Exception exception) {
            logger.error("App - deleteZzim service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
