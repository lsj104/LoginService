package com.assignment.domain.user.controller;

import static com.assignment.domain.global.response.SuccessResponse.success;
import static com.assignment.domain.user.message.SuccessMessage.SIGNUP_SUCCESS_USER;

import com.assignment.domain.global.response.CommonResponse;
import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;
import com.assignment.domain.user.service.UserService;
import com.assignment.domain.user.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
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
}
