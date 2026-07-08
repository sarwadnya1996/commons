package com.example.commons.intercetor;

import com.example.commons.annotation.Authenticate;
import com.example.commons.validaator.JwtValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JwtProtectionInterceptor implements HandlerInterceptor {

    public final JwtValidator validator;
    public final ObjectMapper objectMapper = new ObjectMapper();

    public JwtProtectionInterceptor(JwtValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)  throws Exception{
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        boolean isProtected = handlerMethod.hasMethodAnnotation(Authenticate.class) ||
                handlerMethod.getBeanType().isAnnotationPresent(Authenticate.class);

        if (!isProtected) {
            return true;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Missing or Malformed JWT");
            return false;
        }
        String token = authHeader.substring(7);
        validator.validateJwtToken(token);
        return true;
    }

    private void writeError(HttpServletResponse response, int scUnauthorized, String missingOrMalformedJwt) throws Exception {
        response.setStatus(scUnauthorized);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Map.of("error", missingOrMalformedJwt));
    }
}

