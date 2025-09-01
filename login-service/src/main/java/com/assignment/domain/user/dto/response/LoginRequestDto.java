package com.assignment.domain.user.dto.response;

public record LoginRequestDto(
        String username,
        String password
) {}
