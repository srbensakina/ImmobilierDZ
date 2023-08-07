package com.a2r.immobilierdz.registration;

import com.a2r.immobilierdz.appuser.AppUserRole;
import com.a2r.immobilierdz.realestate.enums.EnumValidator;
import com.a2r.immobilierdz.realestate.enums.Type;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    @EnumValidator(enumClass = AppUserRole.class,
            message = "The type must either be OWNER or USER")
    private AppUserRole appUserRole;
}
