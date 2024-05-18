package com.nodian.adapter.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nodian.adapter.shared.errors.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestExceptionHandlerAdvice {

    @SneakyThrows
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseEntityResponse entityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E004.getCode(), ErrorCode.E004.getMessage(), e.getMessage(), null));
    }

    @SneakyThrows
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseEntityResponse badRequestException(BadRequestException e) {
        log.error("BadRequestException {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E002.getCode(), ErrorCode.E002.getMessage(), e.getMessage(), null));
    }

    @SneakyThrows
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseEntityResponse forbiddenException(AccessDeniedException e) {
        log.error("AccessDeniedException {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E003.getCode(), ErrorCode.E003.getMessage(), e.getMessage(), null));
    }

    @SneakyThrows
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseEntityResponse authenticateException(AuthenticationException e) {
        log.error("AuthenticationException {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E001.getCode(), ErrorCode.E001.getMessage(), e.getMessage(), null));
    }

    @SneakyThrows
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseEntityResponse invalidPayloadException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException {}", e.getMessage(), e);
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E006.getCode(), ErrorCode.E006.getMessage(), null, errors));
    }

    @SneakyThrows
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseEntityResponse generalException(Exception e) {
        log.error("Exception {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E005.getCode(), ErrorCode.E005.getMessage(), null, null));
    }

    @SneakyThrows
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseEntityResponse constraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException {}", e.getMessage(), e);
        return BaseEntityResponse.error(new BaseErrorResponse(ErrorCode.E007.getCode(), ErrorCode.E007.getMessage(), e.getMessage(), null));
    }
}
