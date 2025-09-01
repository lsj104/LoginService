package com.assignment.domain.user.dto.response;

import com.assignment.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SignUpUserResponseDto(
        String username,
        String email,
        LocalDateTime createdAt
) {
    public static SignUpUserResponseDto from(User user) {
        return SignUpUserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail().getValue())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
