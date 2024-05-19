package com.nodian.adapter.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseErrorResponse {
    private String code;
    private String message;
    private String description;
    private List<String> errors;

    public static BaseErrorResponse error(BaseErrorResponse error) {
        return BaseErrorResponse.builder()
                .code(error.code)
                .message(error.message)
                .description(error.description)
                .errors(error.errors)
                .build();
    }
}
