package org.yagna.samples.jwt.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.yagna.samples.jwt.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by asish on 4/4/16.
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService authenticationService;

    public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = authenticationService.getAuthentication(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
