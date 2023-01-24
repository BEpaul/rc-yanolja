package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.src.cart.model.DeleteCartRes;
import com.example.demo.src.cart.model.PostCartReq;
import com.example.demo.src.cart.model.PostCartRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CartService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CartDao cartDao;
    private final CartProvider cartProvider;

    @Autowired
    public CartService(CartDao cartDao, CartProvider cartProvider) {
        this.cartDao = cartDao;
        this.cartProvider = cartProvider;
    }

    @Transactional
    public PostCartRes createCart(PostCartReq postCartReq) throws BaseException {
        // 중복 체크 - 숙소id와 사용날짜가 같은 경우
        if (cartProvider.checkDuplicateCart(postCartReq.getRoomId(), postCartReq.getUseDay()) == 1) {
            throw new BaseException(POST_CARTS_EXISTS_CONTENT);
        }

        try {
            long cartId = cartDao.createCart(postCartReq);
            return new PostCartRes(cartId);
        } catch (Exception exception) {
            logger.error("App - createCart service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public DeleteCartRes deleteCart(Long cartId) throws BaseException {
        // 예외 처리 - 해당 장바구니가 존재하지 않는 경우
        if (cartProvider.checkCartByCartId(cartId) != 1) {
            throw new BaseException(DELETE_CARTS_NOT_EXISTS);
        }

        try {
            long deleteCartId = cartDao.deleteCart(cartId);
            return new DeleteCartRes(deleteCartId);
        } catch (Exception exception) {
            logger.error("App - deleteCart service error", exception);
            throw new BaseException(DATABASE_ERROR);
        }


    }


}
