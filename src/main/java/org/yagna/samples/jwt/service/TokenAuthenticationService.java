package org.yagna.samples.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.yagna.samples.jwt.auth.TokenHandler;
import org.yagna.samples.jwt.auth.UserAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by asish on 4/4/16.
 */

@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, this.tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = this.tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
