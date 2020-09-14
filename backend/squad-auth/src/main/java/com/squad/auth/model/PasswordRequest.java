package com.squad.auth.model;

import lombok.Data;

@Data
public class PasswordRequest {

    private String username, password, confirmationCode, oldPassword, accessToken;

}
