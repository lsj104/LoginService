package com.assignment.domain.user.service;

import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;
import com.assignment.domain.user.model.UserEmail;

public interface UserService {

    // 회원가입
    SignUpUserResponseDto signUpUser(UserSignUpDto userSignUpDto);

    void checkDuplicatedUsername(String username);

    void checkDuplicatedEmail(UserEmail userEmail);


}
