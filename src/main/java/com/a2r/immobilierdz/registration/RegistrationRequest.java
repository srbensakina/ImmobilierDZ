package com.a2r.immobilierdz.registration;

import com.a2r.immobilierdz.appuser.AppUserRole;
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
   // @Email(message ="Invalid email" , regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$i")
   // @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$i", message = "Invalid email")
   @Email(message = "Invalid email", regexp = "(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    private String email;
    @NotBlank(message = "password must not be empty")
    private String password;
    @NotNull(message = "role must not be empty")
   // @EnumValidator(enumClass = AppUserRole.class, message = "The type must either be OWNER or USER")
    private AppUserRole appUserRole;
}
