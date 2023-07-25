package com.a2r.immobilierdz.realestate.enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidator annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        //context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The value must be one of " + acceptedValues)
                .addConstraintViolation();
        return acceptedValues.contains(value.toString());


    }
}