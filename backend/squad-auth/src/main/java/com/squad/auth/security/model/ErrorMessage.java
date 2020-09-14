package com.squad.auth.security.model;

import lombok.Data;

@Data
public class ErrorMessage {

    private String message, code, detail;


    public ErrorMessage(String message , String code , String detail) {
        super();
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    public ErrorMessage(String message) {
        this(message , "" , "");
    }
}
