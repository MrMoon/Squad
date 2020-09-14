package com.squad.auth.security.config;

import com.squad.auth.security.filter.AwsCognitoJwtAuthenticationFilter;
import com.squad.auth.security.filter.MyCorsFilter;
import com.squad.auth.security.filter.RestAccessDeniedHandler;
import com.squad.auth.security.filter.SecurityAuthenticationEntryPoint;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider authProvider;
    private final AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfiguration(CustomAuthenticationProvider authProvider , AwsCognitoJwtAuthenticationFilter awsCognitoJwtAuthenticationFilter) {
        super(true);
        this.authProvider = authProvider;
        this.awsCognitoJwtAuthenticationFilter = awsCognitoJwtAuthenticationFilter;
    }

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider).eraseCredentials(false);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(@NotNull WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth");
        web.ignoring().antMatchers("/auth/**");
        web.ignoring().antMatchers(HttpMethod.OPTIONS , "/**");
    }

    @Override
    protected void configure(@NotNull HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterAfter(corsFilter() , ExceptionTranslationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .and()
                .anonymous()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(awsCognitoJwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);
    }

    @Contract(" -> new")
    private @NotNull MyCorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader(HttpHeaders.ORIGIN);
        config.addAllowedHeader(HttpHeaders.CONTENT_TYPE);
        config.addAllowedHeader(HttpHeaders.ACCEPT);
        config.addAllowedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**" , config);
        return new MyCorsFilter();
    }

}
