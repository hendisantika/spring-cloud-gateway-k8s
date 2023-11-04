package com.hendisantika.categoryservice.controller;

import com.hendisantika.categoryservice.exception.MessageNotFoundException;
import com.hendisantika.categoryservice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.hendisantika.categoryservice.model.ConstantValue.NOT_FOUND_MESSAGE;
import static com.hendisantika.categoryservice.model.ConstantValue.NOT_FOUND_STATUS;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 08:49
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotfoundException(MessageNotFoundException e) {
        log.info("handleNotfoundException.error = {} ", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .errorMessage(NOT_FOUND_MESSAGE)
                        .errorStatus(NOT_FOUND_STATUS)
                        .build());
    }

}
