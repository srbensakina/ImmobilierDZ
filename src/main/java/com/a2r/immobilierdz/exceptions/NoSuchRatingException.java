package com.a2r.immobilierdz.exceptions;

public class NoSuchRatingException extends RuntimeException {

    public NoSuchRatingException(String message) {
        super(message);
    }

    public NoSuchRatingException(String message, Throwable cause) {
        super(message, cause);
    }
}

