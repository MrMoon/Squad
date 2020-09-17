package com.squad.gateway.config.security;

import com.squad.gateway.config.aws.AwsCognitoJwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AwsCognitoJwtAuthFilter awsCognitoJwtAuthFilter;

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("**/health").permitAll()
                .antMatchers("/api/**").authenticated()
                .and().addFilterBefore(awsCognitoJwtAuthFilter , UsernamePasswordAuthenticationFilter.class);
    }
}
