package com.fstg.mediatech.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IpAdressValidation.class})
public @interface IpAdress {

    String message()  default "IpAdress invalide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
