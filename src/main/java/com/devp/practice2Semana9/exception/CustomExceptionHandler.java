package com.devp.practice2Semana9.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(-1)
public class CustomExceptionHandler {

    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public ResponseEntity<Object> handleErrorCustomException(ExceptionNotFoundEntity e) {
        System.out.println("Exception handled in CustomExceptionHandler");
        System.out.println(e.getMessage());
        CustomErrorResponse errorResponse = new CustomErrorResponse("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

}
