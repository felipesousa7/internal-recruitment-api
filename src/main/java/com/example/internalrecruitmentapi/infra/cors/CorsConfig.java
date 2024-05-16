package com.example.internalrecruitmentapi.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir requisições de todas as origens
                .allowedMethods("*") // Permitir todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*"); // Permitir todos os headers
    }
}