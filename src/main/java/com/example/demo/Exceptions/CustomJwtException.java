package com.example.demo.Exceptions;


public class CustomJwtException extends RuntimeException {
    
    // Constructor with message only
    public CustomJwtException(String message) {
        super(message);
    }

    // Constructor with message and cause
    public CustomJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
