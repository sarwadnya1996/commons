package com.example.commons.config;

import com.example.commons.intercetor.JwtProtectionInterceptor;
import com.example.commons.validaator.JwtValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtProtectionConfig implements WebMvcConfigurer {

    @Bean
    public JwtValidator validator(){
        return new JwtValidator();
    }
    @Bean
    public JwtProtectionInterceptor interceptor(JwtValidator validator){
        return new JwtProtectionInterceptor(validator);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor(validator()))
                .addPathPatterns("/api/**");
    }
}
