package com.ga.concurrency.exceptions;

/**
 * Thrown when an uploaded file is invalid (empty or not a .csv file).
 * It is handled by GlobalExceptionHandler and returned as a 400 response.
 */
public class InvalidFileException extends RuntimeException {
    /**
     * Creates the exception with a message describing the problem.
     *
     * @param message the reason the file is invalid
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
