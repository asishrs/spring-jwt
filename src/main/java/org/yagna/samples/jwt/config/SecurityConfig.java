package org.yagna.samples.jwt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.yagna.samples.jwt.filter.JWTTokenAuthFilter;
import org.yagna.samples.jwt.filter.StatelessAuthenticationFilter;
import org.yagna.samples.jwt.service.TokenAuthenticationService;
import org.yagna.samples.jwt.service.UserService;

import java.util.List;


/**
 * Created by asish on 3/28/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public SecurityConfig() {
        super(true);
//        this.userService = new UserService();
//        tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);
    }

    //    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        LOG.debug("****************** Inside security configureGlobal");
//        auth
//            .inMemoryAuthentication()
//                .withUser("user@test.com")  // #1
//                    .password("test123")
//                    .roles("USER")
//                    .and()
//                .withUser("admin@test.com") // #2
//                    .password("test123")
//                    .roles("ADMIN","USER");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        LOG.debug("****************** Inside security configure");

//        http
//                .httpBasic().and().authorizeRequests()
//                .antMatchers("/api/login","/ping","/resources/**","/**").permitAll() // #4
//                .antMatchers("/api/**", "/profile.html").authenticated()// 7
//                .and()
//                .csrf().disable();

        http
            .exceptionHandling().and()
            .anonymous().and()
            .servletApi().and()
//            .headers().cacheControl().and()
            .authorizeRequests()

            // Allow anonymous resource requests
            .antMatchers("/").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/resources/**/*.css").permitAll()
            .antMatchers("/resources/**/*.js").permitAll()
            .antMatchers("/resources/**/*.png", "/resources/**/*.gif").permitAll()
            .antMatchers("/resources/**/*.woff", "/resources/**/*.ttf").permitAll()
            .antMatchers("/*.html").permitAll()

            // Allow anonymous logins
            .antMatchers("/api/login").permitAll()
            .antMatchers("/ping").permitAll()

            // All other request need to be authenticated
            .anyRequest().authenticated().and()

            // Custom Token based authentication based on the header previously given to the client
            .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                    UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserService userDetailsService() {
        return userService;
    }

//    @Bean
//    public TokenAuthenticationService tokenAuthenticationService() {
//        return tokenAuthenticationService;
//    }
}
