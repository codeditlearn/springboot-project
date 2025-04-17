package com.project.springboot_project.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig{


    //https://velog.io/@dh1010a/Spring-Spring-Security%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-3.X-%EB%B2%84%EC%A0%84-2
    //복호화 불가능한 단방향 해시암호화
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return new DelegatingPasswordEncoder(idForEncode, encoders); //("bcrypt",new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        //add로 시작하는 명령어는 개별 추가, set으로 시작하는 명령어는 리스트로 여러 개 추가
                        //config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        config.setAllowedOrigins(Arrays.asList("http://react.codeditlearn.com","https://react.codeditlearn.com","http://localhost:3000")); //빌드 시 CORS
                        config.setAllowedMethods(Collections.singletonList("*")); //"GET", "POST", "PUT", "DELETE", "OPTIONS"
                        //config.setAllowedMethods(Array.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"))
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        //config.addExposedHeader("Access-Token"); //Access-Token 헤더 추가
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf(csrf->csrf.disable())

            .httpBasic(Customizer.withDefaults())
            .formLogin(formLogin -> formLogin
                    .loginPage("/login"))
            .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().permitAll()

            );
        return http.build();
    }
}
