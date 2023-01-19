package com.example.demo.src.zzim;

import com.example.demo.src.zzim.model.GetZzimRes;
import com.example.demo.src.zzim.model.PostZzimReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZzimDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ZzimDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 특정 회원 찜 목록 조회 jdbc
    public List<GetZzimRes> getZzimByUserId(Long userId) {
        String getZzimByUserIdQuery = "select * from zzim where user_id = ?";
        Long getZzimByUserIdParams = userId;

        return this.jdbcTemplate.query(getZzimByUserIdQuery,
                (rs, rowNum) -> new GetZzimRes(
                        rs.getLong("zzim_id"),
                        rs.getLong("user_id"),
                        rs.getLong("room_id"),
                        rs.getLong("company_id")),
                getZzimByUserIdParams
        );
    }


    // 찜하기 jdbc
    public Long createZzim(PostZzimReq postZzimReq) {
        String createZzimQuery = "insert into zzim (user_id, room_id, company_id) VALUES (?,?,?)";
        Object[] createZzimParams = new Object[]{postZzimReq.getUserId(), postZzimReq.getRoomId(), postZzimReq.getCompanyId()};

        this.jdbcTemplate.update(createZzimQuery, createZzimParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }


    // 찜 목록에서 삭제 jdbc
    public Long deleteZzim(Long zzimId) {
        String deleteZzimQuery = "delete from zzim where zzim_id = ?";
        Long deleteZzimParams = zzimId;

        this.jdbcTemplate.update(deleteZzimQuery, deleteZzimParams);
        return zzimId;
    }

}
