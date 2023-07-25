package com.a2r.immobilierdz.login;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@Builder
public class ErrorResponse {

    HttpStatus httpStatus;
    String message;

}
