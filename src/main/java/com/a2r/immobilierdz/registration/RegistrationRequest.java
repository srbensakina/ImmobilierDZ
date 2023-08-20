package com.a2r.immobilierdz.registration;

import com.a2r.immobilierdz.appuser.AppUserRole;
import com.a2r.immobilierdz.realestate.enums.EnumValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "first name must not be empty")
    private String firstName;
    @NotBlank(message = "last name must not be empty")

    private String lastName;
    @Email
    private String email;
    @NotBlank(message = "password must not be empty")
    private String password;
    @NotNull(message = "role must not be empty")
   // @EnumValidator(enumClass = AppUserRole.class, message = "The type must either be OWNER or USER")
    private AppUserRole appUserRole;
}
