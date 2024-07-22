package com.example.githubrepofetcher.config;

import com.example.githubrepofetcher.dto.response.ErrorResponse;
import com.example.githubrepofetcher.exception.InvalidAcceptHeaderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RestClientException.class)
    ResponseEntity<ErrorResponse> handleRestClientEx(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found"));
    }

    @ExceptionHandler(value = InvalidAcceptHeaderException.class)
    ResponseEntity<ErrorResponse> handleInvalidAcceptHeaderEx(InvalidAcceptHeaderException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage()));
    }

}
