package com.squad.auth.model;

import lombok.Data;

@Data
public class UserSignUpRequest {

    private String username, password, email, brokerId, firstName, lastName, agreementFlag;

}
