package com.estoque.lelu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000") // URL do frontend
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*");
  }
}
