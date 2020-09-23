package com.squad.gateway.config.security.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CognitoJWT {

    @NotNull
    private String id_token = "";
    @NotNull
    private String access_token = "";
    @NotNull
    private String refresh_token = "";
    @NotNull
    private String token_type = "";
    private int expires_in = 0;


}
