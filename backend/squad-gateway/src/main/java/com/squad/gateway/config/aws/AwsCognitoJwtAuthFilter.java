package com.squad.gateway.config.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor;

    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication;

        try {
            authentication = this.awsCognitoIdTokenProcessor.authenticate((HttpServletRequest) servletRequest);
            if (authentication != null) SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(servletRequest , servletResponse);
    }
}
