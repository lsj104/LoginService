package com.assignment.domain.global.response;

import lombok.NonNull;

public interface CommonResponse {

    boolean success();

    @NonNull
    String message();

}
