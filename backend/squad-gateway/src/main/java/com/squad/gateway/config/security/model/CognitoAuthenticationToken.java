package com.squad.gateway.config.security.model;

import com.nimbusds.jwt.JWTClaimsSet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class CognitoAuthenticationToken extends AbstractAuthenticationToken {

    @Getter
    @Setter
    List<GrantedAuthority> grantedAuthorities;
    @Getter
    @Setter
    private String token;

    public CognitoAuthenticationToken(String token , JWTClaimsSet details , List<GrantedAuthority> grantedAuthorities) {
        super(grantedAuthorities);
        this.token = token;
        this.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.getDetails();
    }
}
