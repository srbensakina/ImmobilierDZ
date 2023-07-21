package com.a2r.immobilierdz.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class EmailValidator implements Predicate<String> {


    @Override
    public boolean test(String s) {
        //TODO : Vlidate email
        return true;
    }
}
