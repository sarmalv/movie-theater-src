package com.jpmc.theater.exceptions;

import lombok.Getter;

/**
 * Exception class for Handling Various error cases
 */
@Getter
public class MovieException extends RuntimeException {
    private int errorCode;

    public MovieException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
