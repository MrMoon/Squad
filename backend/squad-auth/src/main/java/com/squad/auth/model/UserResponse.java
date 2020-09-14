package com.squad.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserResponse {

    private String username, brokerId, email, userCreateDate, userStatus, lastModifiedDate, firstName, lastName;

}
