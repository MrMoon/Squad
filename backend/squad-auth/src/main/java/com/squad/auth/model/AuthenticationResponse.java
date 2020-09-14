package com.squad.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AuthenticationResponse {

    private String accessToken, sessionToken, expiresIn, actualDate, expirationDate, username, message;
    private UserResponse userResponse;

    public AuthenticationResponse() {
        super();
    }

    public AuthenticationResponse(String accessToken , String expiresIn , String sessionToken , UserResponse userResponse) {
        super();
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.sessionToken = sessionToken;
        this.userResponse = userResponse;
    }

}
