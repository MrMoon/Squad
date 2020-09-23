package com.squad.gateway.config.security.filter;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.squad.gateway.config.security.model.CognitoAuthenticationToken;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * Auth filter for Spring Security
 */
public class AuthFilter extends BasicAuthenticationFilter {

    private ConfigurableJWTProcessor<SecurityContext> processor;

    public AuthFilter(AuthenticationManager authenticationManager , AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager , authenticationEntryPoint);
    }

    public AuthFilter(ConfigurableJWTProcessor<SecurityContext> processor , AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.processor = processor;
    }

    public final ConfigurableJWTProcessor<SecurityContext> getProcessor() {
        return this.processor;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest req , HttpServletResponse res , @NotNull FilterChain chain) throws IOException {
        try {
            String token = extractToken(req.getHeader("Authorization"));
            SecurityContextHolder.getContext().setAuthentication(this.extractAuthentication(token));
            chain.doFilter(req , res);
        } catch (AccessDeniedException e) {
            LoggerFactory.getLogger(this.getClass()).error("Access denied");
            res.setStatus(401);
            res.getWriter().write("Access denied");
        } catch (ServletException e) {
            LoggerFactory.getLogger(this.getClass()).error("ServletException");
            res.setStatus(500);
            res.getWriter().write("ServletException");
        }
    }

    @Contract(pure = true)
    private @Nullable String extractToken(String header) {
        if (header == null) return null;
        String[] headers = header.split("Bearer ");
        return headers.length < 2 ? null : headers[1];
    }

    private CognitoAuthenticationToken extractAuthentication(String token) throws AccessDeniedException {
        if (token == null) return null;

        try {
            return new CognitoAuthenticationToken(token , processor.process(token , null) , null);
        } catch (Exception ex) {
            throw new AccessDeniedException(ex.getMessage());
        }
    }
}
