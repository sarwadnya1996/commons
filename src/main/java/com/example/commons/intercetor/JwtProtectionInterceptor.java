package com.example.commons.intercetor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtProtectionInterceptor implements HandlerInterceptor {

    @Override
    public boolean  preHandle(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler){

    }
}
