package org.yagna.samples.jwt.auth;


import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by asish on 4/4/16.
 */
public class UserAuthentication implements Authentication {

    private final User user;

    private boolean authenticated = true;

    public UserAuthentication(User user) {
        this.user = user;
    }

    public String getName() {
        return user.getUsername();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    public Object getCredentials() {
        return user.getPassword();
    }

    public User getDetails() {
        return user;
    }

    public Object getPrincipal() {
        return user.getUsername();
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
