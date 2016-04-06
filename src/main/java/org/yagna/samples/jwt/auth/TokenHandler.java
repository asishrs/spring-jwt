package org.yagna.samples.jwt.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.yagna.samples.jwt.service.UserService;

/**
 * Created by asish on 4/4/16.
 */
@Component
public final class TokenHandler {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${secret}")
    private String secret;

    @Autowired
    private UserService userService;

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        LOG.info("User {} is accessing app.", username);
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
