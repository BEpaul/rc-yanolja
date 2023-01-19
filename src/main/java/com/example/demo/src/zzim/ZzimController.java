package com.example.demo.src.zzim;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.zzim.model.GetZzimRes;
import com.example.demo.src.zzim.model.PostZzimReq;
import com.example.demo.src.zzim.model.PostZzimRes;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/zzims")
public class ZzimController {

    private final ZzimProvider zzimProvider;
    private final ZzimService zzimService;

    @Autowired
    public ZzimController(ZzimProvider zzimProvider, ZzimService zzimService) {
        this.zzimProvider = zzimProvider;
        this.zzimService = zzimService;
    }


    /**
     * 특정 회원 찜 목록 조회 API
     */
    @Operation(summary = "특정 회원 찜 목록 조회")
    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<List<GetZzimRes>> getZzimByUserId(@PathVariable("userId") Long userId) {
        try {
            List<GetZzimRes> getZzimResList = zzimProvider.getZzimByUserId(userId);
            return new BaseResponse<>(getZzimResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 찜 하기 API
     */
    @Operation(summary = "찜하기")
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostZzimRes> createZzim(@RequestBody PostZzimReq postZzimReq) {
        try {
            PostZzimRes postZzimRes = zzimService.createZzim(postZzimReq);
            return new BaseResponse<>(postZzimRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 찜 목록에서 삭제 API
     */
    @Operation(summary = "찜 목록에서 삭제")
    @ResponseBody
    @DeleteMapping("/{zzimId}")
    public BaseResponse<Long> deleteZzim(@PathVariable("zzimId") Long zzimId) {
        try {
            Long deleteZzimId = zzimService.deleteZzim(zzimId);
            return new BaseResponse<>(deleteZzimId);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
