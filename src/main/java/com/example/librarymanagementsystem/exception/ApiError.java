package com.example.librarymanagementsystem.exception;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

// ApiError.java
@Data
@ToString
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
