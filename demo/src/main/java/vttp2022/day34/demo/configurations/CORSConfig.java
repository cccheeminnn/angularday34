package vttp2022.day34.demo.configurations;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfig implements WebMvcConfigurer{
    
    private String path;
    private String origins;
    public CORSConfig(String p, String o) {
        path = p; // "/api"
        origins = o; // "*"
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping(path)
            .allowedOrigins(origins);
            // .allowedHeaders("*")
            // .allowCredentials(true);
    }



}
