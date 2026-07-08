package com.example.commons.validaator;

import ch.qos.logback.core.util.StringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JwtValidator {
    private final JWTVerifier verifier;
    private static  final String jwtSecret="super_secret_jwt_must_be_at_least_265_bits_long";


    public JwtValidator() {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm).build();
    }

    public void validateJwtToken(String bearerToken) {
        if (StringUtil.isNullOrEmpty(bearerToken)) {
            throw new RuntimeException("Invalid JWT or JWT is not present in header");
        }
        verifier.verify(bearerToken);
    }
}
