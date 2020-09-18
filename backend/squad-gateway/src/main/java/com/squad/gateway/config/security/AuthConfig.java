package com.squad.gateway.config.security;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.squad.gateway.config.security.filter.AuthFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final ConfigurableJWTProcessor<SecurityContext> processor;

    public AuthConfig(ConfigurableJWTProcessor<SecurityContext> processor) {
        super();
        this.processor = processor;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET , "/auth/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthFilter(processor , authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public ConfigurableJWTProcessor<SecurityContext> getProcessor() {
        return this.processor;
    }

}
