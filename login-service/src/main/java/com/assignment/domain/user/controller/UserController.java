package com.assignment.domain.user.controller;

import static com.assignment.domain.global.response.SuccessResponse.success;
import static com.assignment.domain.user.message.SuccessMessage.SELECT_SUCCESS_USER;
import static com.assignment.domain.user.message.SuccessMessage.SIGNUP_SUCCESS_USER;

import com.assignment.domain.global.response.CommonResponse;
import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.GetUserResponseDto;
import com.assignment.domain.user.dto.response.LoginRequestDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;
import com.assignment.domain.user.service.UserService;
import com.assignment.domain.user.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? extends CommonResponse> signUpUser(
            @RequestBody UserSignUpDto requestDto) {

        SignUpUserResponseDto responseDto = userService.signUpUser(requestDto);

        return ResponseEntity
                .status(SIGNUP_SUCCESS_USER.getHttpStatus())
                .body(success(SIGNUP_SUCCESS_USER.getMessage(), responseDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<? extends CommonResponse> login(
            @RequestBody LoginRequestDto req,
            HttpSession session
    ) {
        Long userId = userService.loginAndGetUserId(req);
        session.setAttribute("LOGIN_USER_ID", userId);
        return ResponseEntity.ok(success("로그인 성공"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<? extends CommonResponse> getUser(
            @PathVariable Long userId,
            HttpSession session
    ) {
        Long requesterId = (Long) session.getAttribute("LOGIN_USER_ID");
        GetUserResponseDto dto = userService.getUser(userId, requesterId);
        return ResponseEntity
                .status(SELECT_SUCCESS_USER.getHttpStatus())
                .body(success(SELECT_SUCCESS_USER.getMessage(), dto));
    }

    // 내 정보 조회
    @GetMapping("/me")
    public ResponseEntity<? extends CommonResponse> getMe(HttpSession session) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER_ID");
        if (loginUserId == null) {
            return ResponseEntity
                    .status(org.springframework.http.HttpStatus.UNAUTHORIZED)
                    .body(success("로그인이 필요합니다.", null));
        }

        GetUserResponseDto dto = userService.getUser(loginUserId, loginUserId);
        return ResponseEntity
                .status(SELECT_SUCCESS_USER.getHttpStatus())
                .body(success(SELECT_SUCCESS_USER.getMessage(), dto));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<? extends CommonResponse> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(success("로그아웃 성공", null));
    }

}
