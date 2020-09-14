package com.squad.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.proc.BadJOSEException;
import com.squad.auth.controller.ExceptionController;
import com.squad.auth.exception.CognitoException;
import com.squad.auth.security.model.ResponseWrapper;
import com.squad.auth.util.CorsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class AwsCognitoJwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String ERROR_OCCURED_WHILE_PROCESSING_THE_TOKEN = "Error occured while processing the token";
    private static final String INVALID_TOKEN_MESSAGE = "Invalid Token";
    @Autowired
    private final AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor;
    @Autowired
    private ApplicationContext appContext;

    public AwsCognitoJwtAuthenticationFilter(AwsCognitoIdTokenProcessor awsCognitoIdTokenProcessor) {
        this.awsCognitoIdTokenProcessor = awsCognitoIdTokenProcessor;
    }


    private void createExceptionResponse(ServletRequest request , ServletResponse response , CognitoException exception) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        ExceptionController exceptionController = null;
        ObjectMapper objMapper = new ObjectMapper();

        exceptionController = appContext.getBean(ExceptionController.class);
        ResponseWrapper responseWrapper = exceptionController.handleJwtException(req , exception);
        HttpServletResponse httpResponse = CorsHelper.addResponseHeaders(response);

        final HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse);
        wrapper.setStatus(HttpStatus.UNAUTHORIZED.value());
        wrapper.setContentType(MediaType.APPLICATION_JSON_VALUE);
        wrapper.getWriter().println(objMapper.writeValueAsString(responseWrapper));
        wrapper.getWriter().flush();

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = null;
        try {
            authentication = awsCognitoIdTokenProcessor.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadJOSEException e) {
            SecurityContextHolder.clearContext();
            e.printStackTrace();
            createExceptionResponse(request , response , new CognitoException(INVALID_TOKEN_MESSAGE , CognitoException.INVALID_TOKEN_EXCEPTION_CODE , e.getMessage()));
            return;
        } catch (CognitoException e) {
            SecurityContextHolder.clearContext();
            e.printStackTrace();
            createExceptionResponse(request , response , new CognitoException(e.getErrorMessage() , CognitoException.INVALID_TOKEN_EXCEPTION_CODE , e.getDetailErrorMessage()));
            return;
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            e.printStackTrace();
            createExceptionResponse(request , response , new CognitoException(ERROR_OCCURED_WHILE_PROCESSING_THE_TOKEN , CognitoException.INVALID_TOKEN_EXCEPTION_CODE , e.getMessage()));
            return;
        }
        filterChain.doFilter(request , response);
    }

}
