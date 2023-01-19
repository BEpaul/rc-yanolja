package com.example.demo.src.cart;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.cart.model.DeleteCartRes;
import com.example.demo.src.cart.model.GetCartRes;
import com.example.demo.src.cart.model.PostCartReq;
import com.example.demo.src.cart.model.PostCartRes;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/carts")
public class CartController {

    private final CartProvider cartProvider;
    private final CartService cartService;

    @Autowired
    public CartController(CartProvider cartProvider, CartService cartService) {
        this.cartProvider = cartProvider;
        this.cartService = cartService;
    }

    /**
     * 특정 회원 장바구니 조회 API
     */
    @Operation(summary = "특정 회원 장바구니 조회")
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<List<GetCartRes>> getCartByUserId(@PathVariable("userId") Long userId) {
        try {
            List<GetCartRes> getCartResList = cartProvider.getCartByUserId(userId);
            return new BaseResponse<>(getCartResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 장바구니 생성 API
     */
    @Operation(summary = "장바구니 생성")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostCartRes> createCart(@RequestBody PostCartReq postCartReq) {
        try {
            PostCartRes postCartRes = cartService.createCart(postCartReq);
            return new BaseResponse<>(postCartRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 장바구니 특정 상품 삭제 API
     */
    @Operation(summary = "장바구니 특정 상품 삭제")
    @ResponseBody
    @DeleteMapping("/{cartId}")
    public BaseResponse<DeleteCartRes> deleteCart(@PathVariable("cartId") Long cartId) {
        try {
            DeleteCartRes deleteCartRes = cartService.deleteCart(cartId);
            return new BaseResponse<>(deleteCartRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
