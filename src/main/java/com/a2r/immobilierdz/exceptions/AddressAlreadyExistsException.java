package com.a2r.immobilierdz.exceptions;

public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException(String message) {
        super(message);
    }

    public AddressAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

