package com.example.librarymanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {



        @Bean
        public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();

            // Allow specific origins
            config.addAllowedOrigin("http://localhost");


            // Allow specific HTTP methods
            config.addAllowedMethod("GET");
            config.addAllowedMethod("POST");
            config.addAllowedMethod("PUT");
            config.addAllowedMethod("DELETE");

            // Allow specific headers
            config.addAllowedHeader("Authorization");
            config.addAllowedHeader("Content-Type");

            // Allow credentials (cookies, authorization headers)
            config.setAllowCredentials(true);

            // Set the maximum age of the preflight request in seconds
            config.setMaxAge(3600L);

            source.registerCorsConfiguration("/api/**", config);
            return new CorsFilter(source);
        }
    }

