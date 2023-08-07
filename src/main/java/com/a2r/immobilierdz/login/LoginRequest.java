package com.a2r.immobilierdz.login;


import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
