package com.ga.concurrency.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions thrown by the controllers and turns them into
 * HTTP responses instead of a 500 error with a stack trace.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Returns a 400 Bad Request with the error message when an uploaded file is empty or is not a .csv file.
     *
     * @param ex the exception thrown by FileService
     * @return 400 response whose body is the exception message
     */
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<String> handleInvalidFile(InvalidFileException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
