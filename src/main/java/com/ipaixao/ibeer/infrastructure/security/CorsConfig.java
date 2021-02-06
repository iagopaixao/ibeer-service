package com.ipaixao.ibeer.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] ALLOWED_METHODS;
    static {
        ALLOWED_METHODS = new String[]{"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"};
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600)
                .allowedMethods(ALLOWED_METHODS);
    }
}