package com.libraryman_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static com.libraryman_api.member.Role.ADMIN;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity(debug = true) // Do not use (debug=true) in a production system! as this contain sensitive information.
public class WebConfiguration {

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                                // make sure it is in order to access the proper Url

                                .requestMatchers("/signup").permitAll()
                                .requestMatchers("/login").permitAll()
                )
//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .formLogin(withDefaults());
        return http.build();
    }
}
