package com.squad.gateway.config.security.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String uuid, given_name, family_name, cognitoUsername, email, gender, birthdate;
    private Date issuedAt, expiresIn;
    private Long authTime;

}
