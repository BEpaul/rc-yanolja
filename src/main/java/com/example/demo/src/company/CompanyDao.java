package com.example.demo.src.company;

import com.example.demo.src.company.model.GetCompanyRes;
import com.example.demo.src.company.model.PostCompanyReq;
import com.example.demo.src.company.model.DeleteCompanyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 숙소 전체 조회 jdbc
//    public List<GetCompanyRes> getCompanyAll() {
//        String getCompanyAllQuery = "select * from company";
//        return this.jdbcTemplate.query(getCompanyAllQuery,
//                (rs, rowNum) -> new GetCompanyRes(
//                        rs.getLong("company_id"),
//                        rs.getString("company_category"),
//                        rs.getString("company_name"),
//                        rs.getString("company_event"),
//                        rs.getString("company_service"),
//                        rs.getString("company_policy"))
//        );
//    }
//
//    // 숙소 조회 jdbc (카테고리 필터링)
//    public List<GetCompanyRes> getCompanyByCategory(String category) {
//        String getCompanyByCategoryQuery = "select * from company where category = ?";
//        String getCompanyByCategoryParams = category;
//
//        return this.jdbcTemplate.query(getCompanyByCategoryQuery,
//                (rs, rowNum) -> new GetCompanyRes(
//                        rs.getLong("company_id"),
//                        rs.getString("company_category"),
//                        rs.getString("company_name"),
//                        rs.getString("company_event"),
//                        rs.getString("company_service"),
//                        rs.getString("company_policy")),
//                getCompanyByCategoryParams
//        );
//    }

    // 특정 숙소 조회 jdbc
    public GetCompanyRes getCompanyById(Long companyId) {
        String getCompanyByIdQuery = "select c.company_id, c.company_category, ci.image_url from company c join company_image ci on c.company_id = ci.company_id where c.company_id = ?";
        Long getCompanyByIdParams = companyId;

        return this.jdbcTemplate.queryForObject(getCompanyByIdQuery,
                (rs, rowNum) -> new GetCompanyRes(
                        rs.getLong("company_id"),
                        rs.getString("company_category"),
                        rs.getString("image_url")
//                        rs.getString("company_name"),
//                        rs.getString("company_event"),
//                        rs.getString("company_service"),
//                        rs.getString("company_policy")
                ),
                getCompanyByIdParams);
    }

    // 숙소 생성 jdbc
    public Long createCompany(PostCompanyReq postCompanyReq) {
        String createCompanyQuery = "insert into company (company_category, company_name, company_event, company_service, company_policy) VALUES (?,?,?,?,?)";

        Object[] createCompanyParams = new Object[]{postCompanyReq.getCompanyCategory(), postCompanyReq.getCompanyName(), postCompanyReq.getEvent(), postCompanyReq.getService(), postCompanyReq.getPolicy()};

        this.jdbcTemplate.update(createCompanyQuery, createCompanyParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);

    }


    // 숙소 정보 변경 jdbc

    // 숙소 삭제 jdbc
    public Long modifyCompanyStatus(DeleteCompanyReq deleteCompanyReq) {
        String modifyCompanyStatusQuery = "update company set status = ? where company_id = ?";
        Object[] modifyCompanyStatusParams = new Object[]{deleteCompanyReq.getCompanyId(), deleteCompanyReq.getStatus()};

        return Long.valueOf(this.jdbcTemplate.update(modifyCompanyStatusQuery, modifyCompanyStatusParams));
    }

}
