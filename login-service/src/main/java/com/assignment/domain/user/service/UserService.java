package com.assignment.domain.user.service;

import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.GetUserResponseDto;
import com.assignment.domain.user.dto.response.LoginRequestDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;

public interface UserService {

    // 회원가입
    SignUpUserResponseDto signUpUser(UserSignUpDto userSignUpDto);

    // 로그인
    Long loginAndGetUserId(LoginRequestDto req);

    GetUserResponseDto getUser(Long userId, Long requesterId);

}


