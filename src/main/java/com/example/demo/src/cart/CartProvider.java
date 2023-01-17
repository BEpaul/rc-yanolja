package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.cart.model.GetCartRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CartProvider {
    private final CartDao cartDao;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CartProvider(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    // 특정 회원 장바구니 조회
    public List<GetCartRes> getCartByUserId(Long userId) throws BaseException {
        try {
            List<GetCartRes> getCartResList = cartDao.getCartByUserId(userId);
            return getCartResList;
        } catch (Exception exception) {
            logger.error("App - getCartByUserId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 장바구니 중복 조회
    public int checkDuplicateCart(Long roomId, String useDay) throws BaseException {
        try {
            return cartDao.checkDuplicateCart(roomId, useDay);
        } catch (Exception exception) {
            logger.error("App - checkDuplicateCart provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkCartByCartId(Long cartId) throws BaseException {
        try {
            return cartDao.checkCartByCartId(cartId);
        } catch (Exception exception) {
            logger.error("App - checkCartByCartId provider error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
