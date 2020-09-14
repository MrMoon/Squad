package com.squad.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squad.auth.security.model.ErrorMessage;
import com.squad.auth.security.model.ResponseWrapper;
import com.squad.auth.security.model.RestErrorList;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collections;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse , @NotNull AccessDeniedException e) throws IOException, ServletException {
        RestErrorList errorList = new RestErrorList(SC_FORBIDDEN , new ErrorMessage(e.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null , Collections.singletonMap("status" , SC_FORBIDDEN) , errorList);
        ObjectMapper objMapper = new ObjectMapper();

        final HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpServletResponse);
        wrapper.setStatus(SC_FORBIDDEN);
        wrapper.setContentType(MediaType.APPLICATION_JSON_VALUE);
        wrapper.getWriter().println(objMapper.writeValueAsString(responseWrapper));
        wrapper.getWriter().flush();
    }
}
