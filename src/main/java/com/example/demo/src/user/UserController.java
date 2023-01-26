package com.example.demo.src.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @Operation(summary = "회원 전체 조회")
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
//        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>(getUsersRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userId
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @Operation(summary = "특정 회원 조회")
    @ResponseBody
    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") Long userId) {
        // Get Users
//        try{
            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(getUserRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }

    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @Operation(summary = "회원가입")
    @ResponseBody
    @PostMapping(value = "")
    public BaseResponse<PostUserRes> createUser(@Valid @RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        if(postUserReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        //이메일 정규표현
        if(!isRegexEmail(postUserReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
//        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
    }
    /**
     * 로그인 API
     * [POST] /users/logIn
     * @return BaseResponse<PostLoginRes>
     */
    @Operation(summary = "로그인")
    @ResponseBody
    @PostMapping("/logIn")
    public BaseResponse<PostLoginRes> logIn(@Valid @RequestBody PostLoginReq postLoginReq){
        try{
            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
            PostLoginRes postLoginRes = userProvider.logIn(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userId
     * @return BaseResponse<String>
     */
    @Operation(summary = "유저 정보 변경")
    @ResponseBody
    @PatchMapping("/{userId}")
    public BaseResponse<String> modifyUserName(@Valid @PathVariable("userId") Long userId, @RequestBody User user){
//        try {
            //jwt에서 id 추출.
            Long userIdByJwt = jwtService.getUserId();
            //userId와 접근한 유저가 같은지 확인
            if(userId != userIdByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            //같다면 유저네임 변경
            PatchUserReq patchUserReq = new PatchUserReq(userId ,user.getUserName());
            userService.modifyUserName(patchUserReq);

            String result = "수정 완료";
            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
    }

    /**
     * 유저 탈퇴 API
     * [DELETE] /users/:userId
     *
     * @return BaseException
     */
    @Operation(summary = "회원 탈퇴")
    @ResponseBody
    @DeleteMapping("/{userId}")
    public BaseResponse<String> deleteUser(@Valid @PathVariable("userId") Long userId, @RequestBody User user) {
        // 회원 정보 확인
        Long userIdByJwt = jwtService.getUserId();
        if (userId != userIdByJwt) {
            return new BaseResponse<>(INVALID_USER_JWT);
        }

        // 회원 탈퇴
        DeleteUserReq deleteUserReq = new DeleteUserReq(userId, user.getStatus());
        userService.modifyUserStatus(deleteUserReq);

        String result = "회원 탈퇴 완료";
        return new BaseResponse<>(result);
    }


    @Operation(summary = "카카오 OAuth 로그인")
    @GetMapping("/kakao")
    public void oauthKakao(@RequestParam String code) throws BaseException{
        String accessToken = userService.getKaKaoAccessToken(code);
        userService.createKakaoUser(accessToken);

//        return new BaseResponse<>(accessToken);
    }
}