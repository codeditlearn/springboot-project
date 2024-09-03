package com.project.springboot_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 경로를 설정해 줄 것이기 때문에 /**로 설정
                .allowedMethods("*") //모든 http method를 허용할 것으로 *로 설정 [GET, POST, PUT, DELETE]
                .allowedHeaders("*") //Content-Type ...
                //.exposedHeaders("Access-Token") // 커스텀헤더추가
                .allowCredentials(true)
                //.allowedOrigins("http://localhost:3000"); // Origin이 http:localhost:3000에 대해
                .allowedOrigins("http://react.codeditlearn.com"); // Origin이 http:localhost:3000에 대해


    }
}
