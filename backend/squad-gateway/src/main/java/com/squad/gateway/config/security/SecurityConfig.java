package com.squad.gateway.config.security;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.squad.gateway.config.security.filter.AuthFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${urls.cognito}")
    private String cognitoUrl;

    private final ConfigurableJWTProcessor<SecurityContext> processor;

    public SecurityConfig(ConfigurableJWTProcessor<SecurityContext> processor) {
        super();
        this.processor = processor;
    }

    @Override
    public void configure(@NotNull WebSecurity web) {
        web.ignoring().mvcMatchers("/auth/**");
        web.ignoring().mvcMatchers(this.cognitoUrl + "/**");
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthFilter(processor , authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public ConfigurableJWTProcessor<SecurityContext> getProcessor() {
        return this.processor;
    }

}
