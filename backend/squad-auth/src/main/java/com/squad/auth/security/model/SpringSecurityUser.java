package com.squad.auth.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class SpringSecurityUser implements UserDetails {

    /**
     * Generated serial version
     */
    private static final long serialVersionUID = 4089895915973854812L;
    private String username, password, email, accessToken, tokenType, refreshToken, idToken;
    private Integer expiresIn;
    private Date lastPasswordReset;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean isAccountNonExpired = true, isAccountNonLocked = true, isCredentialsNonExpired = true, isEnabled = true;

    @Override
    public boolean isAccountNonExpired() {
        return this.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
