package com.squad.auth.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username, password, newPassword, accessToken;
}
