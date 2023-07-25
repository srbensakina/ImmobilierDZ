package com.a2r.immobilierdz.registration;

import com.a2r.immobilierdz.appuser.AppUserRole;
import com.a2r.immobilierdz.realestate.enums.EnumValidator;
import com.a2r.immobilierdz.realestate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {


    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @Email
    private final String email;
    @NotBlank
    private final String password;
    @NotNull
    @EnumValidator(enumClass = AppUserRole.class,
            message = "The type must either be OWNER or USER")
    private final AppUserRole appUserRole;
}
