package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private String content;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseDto<T> {
        // HttpStatus status;
        int status;
        T data;


    }
}
