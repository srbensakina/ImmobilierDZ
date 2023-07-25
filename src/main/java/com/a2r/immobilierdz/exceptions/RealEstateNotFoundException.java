package com.a2r.immobilierdz.exceptions;

public class RealEstateNotFoundException extends RuntimeException {

    public RealEstateNotFoundException(String message) {
        super(message);
    }

    public RealEstateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

