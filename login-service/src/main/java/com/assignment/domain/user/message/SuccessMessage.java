package com.assignment.domain.user.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    SIGNUP_SUCCESS_USER(HttpStatus.CREATED, "회원가입 성공"),
    SELECT_SUCCESS_USER(HttpStatus.OK, "회원 조회 성공"),
    SELECT_SUCCESS_USER_LIST(HttpStatus.OK, "회원 전체 조회 성공"),
    DELETE_SUCCESS_USER(HttpStatus.OK, "회원 탈퇴 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
