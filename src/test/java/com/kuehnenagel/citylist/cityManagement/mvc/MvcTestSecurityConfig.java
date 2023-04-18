package com.kuehnenagel.citylist.cityManagement.mvc;

import com.kuehnenagel.citylist.common.security.Role;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@EnableWebSecurity
public class MvcTestSecurityConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails editor = User.builder()
                .username("fabius.bile")
                .password("$2a$12$3CcVHjzcpNLK/Z5wb4gfBOcqjCxs/pRRJuUMLCC2e2sE8zm9wFNJ6")
                .roles(Role.ALLOW_EDIT.name())
                .build();
        return new InMemoryUserDetailsManager(editor);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/cities").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/cities").hasRole(Role.ALLOW_EDIT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }


    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(users());
        return authenticationProvider;

    }

}
