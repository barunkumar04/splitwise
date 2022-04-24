package com.practice.splitwise.utils;

import com.practice.splitwise.dto.ResponseDTO;

import java.util.Optional;

public class ResponseUtils {

    public static <DTO_TYPE> ResponseDTO<DTO_TYPE> toResponse(Optional<DTO_TYPE> data) {
        return data.map(ResponseDTO::success).orElse(ResponseDTO.notFound());
    }
}