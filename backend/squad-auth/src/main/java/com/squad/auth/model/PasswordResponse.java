package com.squad.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PasswordResponse {

    private String destination, deliveryMedium, message, username;

}
