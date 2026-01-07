package com.project.learning.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ResouceNotFound.class)
    public ResponseEntity<?> handleBadRequest(ResouceNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleBadRequest(UnauthorizedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException e){
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->
                errors.put(error.getField(),error.getDefaultMessage()));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

}
