package com.assignment.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserSignUpDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email
) {

}
