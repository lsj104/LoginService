package com.assignment.domain.user.dto.response;

import com.assignment.domain.user.model.User;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetUserResponseDto(
        String username,
        String email,
        LocalDateTime createdAt
) {

    public static GetUserResponseDto from(User user) {
        return GetUserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail().getValue())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
