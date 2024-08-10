package com.user.Service.Exceptions;

import com.user.Service.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {// this project any where throw exception this class will handle any exception

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message =  ex.getMessage();
       ApiResponse response =   ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();

         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);

    }
}
