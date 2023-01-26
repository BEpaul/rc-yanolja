package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.PASSWORD_DECRYPTION_ERROR;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from user";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getLong("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("nickname"),
                        rs.getLong("point"))
        );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from user where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getLong("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("nickname"),
                        rs.getLong("point")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(Long userId){
        String getUserQuery = "select * from user where user_id = ?";
        Long getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getLong("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("Email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("nickname"),
                        rs.getLong("point")),
                getUserParams);
    }


    public Long createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into user (login_id, user_name, email, password, phone_number, nickname) VALUES (?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getLoginId(), postUserReq.getUserName(), postUserReq.getEmail(), postUserReq.getPassword(), postUserReq.getPhoneNumber(),postUserReq.getNickname()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,Long.class);
    }

    public Long modifyUserStatus(DeleteUserReq deleteUserReq) {
        String modifyUserStatusQuery = "update user set status = ? where user_id = ?";
        Object[] modifyUserStatusParams = new Object[]{deleteUserReq.getStatus(), deleteUserReq.getUserId()};

        return Long.valueOf(this.jdbcTemplate.update(modifyUserStatusQuery, modifyUserStatusParams));
    }



    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from user where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update user set user_name = ? where user_id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select user_id, login_id, user_name, email, password, phone_number, nickname, point, created_time, updated_time, status from user where login_id = ?";
        String getPwdParams = postLoginReq.getLoginId();

            return this.jdbcTemplate.queryForObject(getPwdQuery,
                    (rs, rowNum) -> new User(
                            rs.getLong("user_id"),
                            rs.getString("login_id"),
                            rs.getString("user_name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("phone_number"),
                            rs.getString("nickname"),
                            rs.getLong("point"),
                            rs.getTimestamp("created_time").toLocalDateTime(),
                            rs.getTimestamp("updated_time").toLocalDateTime(),
                            rs.getLong("status")),
                    getPwdParams
        );
    }


}