package com.squad.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserSignUpResponse {

    private String username, userCreationDate, lastModifiedDate, userStatus, password, email, brokerId;
    private boolean isEnabled;

}
