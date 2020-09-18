package com.squad.gateway.config.security.model;

import lombok.Data;

import java.util.Date;

@Data
public class TokenClaims {

    private String uuid, name, cognitoUsername, email;
    private Date issuedAt, expiresIn;
    private Long authTime;

}
