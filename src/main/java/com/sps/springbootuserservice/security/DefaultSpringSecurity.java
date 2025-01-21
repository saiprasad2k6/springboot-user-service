package com.sps.springbootuserservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class DefaultSpringSecurity {

    //@Bean
    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {

        http
                .csrf().disable()  // Disables CSRF protection
                //.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/logout").denyAll())
                //.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/*").authenticated())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        return http.build();
    }

}
