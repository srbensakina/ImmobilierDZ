package com.a2r.immobilierdz.registration;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody @Valid RegistrationRequest registrationRequest){
         registrationService.register(registrationRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
