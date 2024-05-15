package com.aquamate.Aquamate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca esta classe como uma configuração do Spring.
public class CorsConfiguration implements WebMvcConfigurer { // Implementa WebMvcConfigurer para customizar o comportamento padrão do Spring MVC.

    @Override // Sobrescreve o método da interface WebMvcConfigurer.
    public void addCorsMappings(CorsRegistry registry) { // Define as regras de CORS.
        registry.addMapping("/**") // Permite CORS para todas as rotas (/**) da aplicação.
                .allowedOrigins("http://127.0.0.1:5501", "http://127.0.0.1:5500") // Permite requisições de CORS nos domínios especificados.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"); // Metodos HTTP permitids nas requisições CORS.
    }
}
