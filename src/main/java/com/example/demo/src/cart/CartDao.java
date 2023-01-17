package com.example.demo.src.cart;

import com.example.demo.src.cart.model.GetCartRes;
import com.example.demo.src.cart.model.PostCartReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 특정 회원 장바구니 조회
    public List<GetCartRes> getCartByUserId(Long userId) {
        String getCartByUserIdQuery = "select * from cart where user_id = ?";
        Long getCartByUserIdParams = userId;
        return this.jdbcTemplate.query(getCartByUserIdQuery,
                (rs, rowNum) -> new GetCartRes(
                        rs.getLong("cart_id"),
                        rs.getLong("user_id"),
                        rs.getString("used_day"),
                        rs.getString("room_name"),
                        rs.getString("room_type"),
                        rs.getLong("normal_price"),
                        rs.getLong("discount_price"),
                        rs.getLong("room_id"),
                        rs.getLong("company_id")),
                getCartByUserIdParams
                );
    }

    // 장바구니 생성 jdbc
    public int createCart(PostCartReq postCartReq) {
        String createCartQuery = "insert into cart (user_id, used_day, room_name, room_type, normal_price, discount_price, room_id, company_id) VALUES (?,?,?,?,?,?,?,?)";
        Object[] createCartParams = new Object[]{postCartReq.getUserId(), postCartReq.getUseDay(), postCartReq.getRoomName(), postCartReq.getRoomType(),
                postCartReq.getNormalPrice(), postCartReq.getDiscountPrice(), postCartReq.getRoomId(), postCartReq.getCompanyId()};

        this.jdbcTemplate.update(createCartQuery, createCartParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 장바구니 삭제 jdbc
    public int deleteCart(Long cartId) {
        String deleteCartQuery = "delete from cart where cart_id = ?";
        Long deleteCartParams = cartId;

        this.jdbcTemplate.update(deleteCartQuery, deleteCartParams);

        return cartId.intValue();
    }

    // 장바구니 중복 체크
    public int checkDuplicateCart(Long roomId, String useDay) {
        String checkDuplicateCartQuery = "select exists (select room_id from cart where room_id = ? and used_day = ?)";
        Object[] checkDuplicateCartParams = new Object[]{roomId, useDay};

        return this.jdbcTemplate.queryForObject(checkDuplicateCartQuery, int.class, checkDuplicateCartParams);
    }

    // 장바구니 존재 여부 체크
    public int checkCartByCartId(Long cartId) {
        String checkCartByCartIdQuery = "select exists (select cart_id from cart where cart_id = ?)";
        Long checkCartByCartIdParams = cartId;

        return this.jdbcTemplate.queryForObject(checkCartByCartIdQuery, int.class, checkCartByCartIdParams);
    }

}
