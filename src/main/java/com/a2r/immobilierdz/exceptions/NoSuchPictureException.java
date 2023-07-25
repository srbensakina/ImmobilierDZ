package com.a2r.immobilierdz.exceptions;

public class NoSuchPictureException extends RuntimeException {

    public NoSuchPictureException(String message) {
        super(message);
    }

    public NoSuchPictureException(String message, Throwable cause) {
        super(message, cause);
    }
}

