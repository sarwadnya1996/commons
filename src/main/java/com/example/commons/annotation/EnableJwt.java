package com.example.commons.annotation;

import com.example.commons.config.JwtProtectionConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JwtProtectionConfig.class)
public @interface EnableJwt {
}
