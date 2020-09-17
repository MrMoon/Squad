package com.squad.gateway.config.jwt;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtIdTokenCredentialsHolder {

    private String idToken;

}
