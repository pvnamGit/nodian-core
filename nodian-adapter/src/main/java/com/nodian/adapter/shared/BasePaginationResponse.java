package com.nodian.adapter.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePaginationResponse<T> {
    private Boolean success;
    private String message;
    private Integer total;
    private T[] data;

    public static<T> BasePaginationResponse success(T[] data, Integer total) {
        return BasePaginationResponse.<T>builder()
                .success(Boolean.TRUE)
                .total(total)
                .data(data)
                .build();
    }

    public static<T> BasePaginationResponse success(T[] data) {
        return BasePaginationResponse.<T>builder()
                .success(Boolean.TRUE)
                .data(data)
                .build();
    }

    public static <T> BasePaginationResponse<T> error(String message) {
        return BasePaginationResponse.<T>builder()
                .success(Boolean.FALSE)
                .message(message)
                .build();
    }
}
