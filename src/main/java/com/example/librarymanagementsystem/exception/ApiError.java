package com.example.librarymanagementsystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

// ApiError.java
@Data
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
