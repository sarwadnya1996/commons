package com.example.commons.annotation;

import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authenticate {
    String message() default "Invalid or expired JWT token";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
