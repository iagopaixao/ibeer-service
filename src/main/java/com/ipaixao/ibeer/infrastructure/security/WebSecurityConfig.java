package com.ipaixao.ibeer.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] ALLOWED_URIs;
    static {
        ALLOWED_URIs = new String[]{
                "/swagger-ui**", "/webjars/springfox-swagger-ui/**", "/swagger-resources/**", "/v2/**",
                "/actuator/health**", "/error**", "/favicon.ico", "/swagger-ui/**", "/v3/**"
        };
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(ALLOWED_URIs)
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(ALLOWED_URIs);
    }
}