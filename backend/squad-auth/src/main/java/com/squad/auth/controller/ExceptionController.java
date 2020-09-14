package com.squad.auth.controller;


import com.squad.auth.exception.CognitoException;
import com.squad.auth.security.model.ErrorMessage;
import com.squad.auth.security.model.ResponseWrapper;
import com.squad.auth.security.model.RestErrorList;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseEntity<ResponseWrapper> handleException(HttpServletRequest request , ResponseWrapper responseWrapper) {
        return ResponseEntity.ok(responseWrapper);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseWrapper> handleIOException(HttpServletRequest request , @NotNull CognitoException e) {
        RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE , new ErrorMessage(e.getErrorMessage() , e.getErrorCode() , e.getDetailErrorMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null , Collections.singletonMap("status" , HttpStatus.NOT_ACCEPTABLE) , errorList);
        return ResponseEntity.ok(responseWrapper);
    }

    public ResponseWrapper handleJwtException(HttpServletRequest request , @NotNull CognitoException e) {
        RestErrorList errorList = new RestErrorList(HttpStatus.UNAUTHORIZED , new ErrorMessage(e.getErrorMessage() , e.getErrorCode() , e.getDetailErrorMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null , Collections.singletonMap("status" , HttpStatus.UNAUTHORIZED) , errorList);
        return responseWrapper;
    }

}
