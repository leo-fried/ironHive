package com.leofriedman.projectmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500") // Allow requests from HTML frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow common HTTP methods
                .allowedHeaders("*");
    }
}
