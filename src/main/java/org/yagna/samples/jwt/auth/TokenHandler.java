package org.yagna.samples.jwt.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.yagna.samples.jwt.service.UserService;

import javax.annotation.PostConstruct;

/**
 * Created by asish on 4/4/16.
 */
@Component
public final class TokenHandler {

    @Value("${secret}")
    private String secret;

    @Autowired
    private UserService userService;

//    public TokenHandler(String secret, UserService userService) {
//        this.secret = secret;
//        this.userService = userService;
//    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
