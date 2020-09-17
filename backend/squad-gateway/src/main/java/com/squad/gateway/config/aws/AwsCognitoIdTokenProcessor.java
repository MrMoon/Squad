package com.squad.gateway.config.aws;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.squad.gateway.config.jwt.JwtAuthentication;
import com.squad.gateway.config.jwt.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AwsCognitoIdTokenProcessor {

    private final JwtConfiguration jwtConfiguration;
    private final ConfigurableJWTProcessor<?> configurableJWTProcessor;

    public Authentication authenticate(@NotNull HttpServletRequest request) throws Exception {
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        if (idToken != null) {
            JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken) , null);
            validateIssuer(claims);
            verifyIfIdToken(claims);
            String username = getUserNameFrom(claims);
            if (username != null) {
                List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                User user = new User(username , "" , List.of());
                return new JwtAuthentication(user , claims , grantedAuthorities);
            }
        }
        return null;
    }

    private String getUserNameFrom(@NotNull JWTClaimsSet claims) {
        return claims.getClaims().get(this.jwtConfiguration.getUserNameField()).toString();
    }

    private void verifyIfIdToken(@NotNull JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl()))
            throw new Exception("JWT Token is not an ID Token");
    }

    private void validateIssuer(@NotNull JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl()))
            throw new Exception(String.format("Issuer %s does not match cognito idp %s" , claims.getIssuer() , this.jwtConfiguration.getCognitoIdentityPoolUrl()));
    }

    private String getBearerToken(@NotNull String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }

}
