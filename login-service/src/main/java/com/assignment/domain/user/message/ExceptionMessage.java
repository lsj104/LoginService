package com.assignment.domain.user.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    USER_EMAIL_VALID(HttpStatus.BAD_REQUEST, "유저 이메일은 3글자 이상이어야 합니다."),
    SIGNUP_DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    SIGNUP_DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    LOGIN_NOT_FOUND_USER(HttpStatus.NOT_FOUND, "로그인 회원이 없습니다."),
    LOGIN_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),
    SELECT_NOT_FOUND_USER(HttpStatus.NOT_FOUND, "조회 회원이 없습니다."),
    UPDATE_DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    UPDATE_DUPLICATED_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
