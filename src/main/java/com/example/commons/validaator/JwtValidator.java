package com.example.commons.validaator;

import ch.qos.logback.core.util.StringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JwtValidator {
    private final JWTVerifier verifier;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    public JwtValidator() {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm).build();
    }

    public void validateJwtToken(String bearerToken) {
        if (StringUtil.isNullOrEmpty(bearerToken)) {
            throw new RuntimeException("Invalid JWT or JWT is not present in header");
        }
        String token = bearerToken.substring(7);
        verifier.verify(token);
    }
}
