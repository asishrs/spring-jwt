package org.yagna.samples.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yagna.samples.jwt.auth.UserAuthentication;
import org.yagna.samples.jwt.model.AuthStatus;
import org.yagna.samples.jwt.model.UserLogin;
import org.yagna.samples.jwt.service.TokenAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asish on 4/2/16.
 */
@RestController
public class AuthenticationController {

    private Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(value="/api/login", method= RequestMethod.POST)
    public AuthStatus login(HttpServletResponse response, @RequestBody UserLogin userLogin) throws IOException {

//        User activeUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.info("UserLogin - {}, Authenticated User - {}", userLogin);
        List<GrantedAuthority> roles =  new ArrayList<GrantedAuthority>();
        User user = new User(userLogin.getUserName(), userLogin.getPassword(), roles);
        UserAuthentication userAuthentication = new UserAuthentication(user);
        tokenAuthenticationService.addAuthentication(response, userAuthentication);
        return new AuthStatus("true");
    }
}
