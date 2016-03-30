package org.yagna.samples.jwt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.yagna.samples.jwt.filter.JWTTokenAuthFilter;


/**
 * Created by asish on 3/28/16.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOG.debug("****************** Inside security configureGlobal");
        auth
            .inMemoryAuthentication()
                .withUser("user")  // #1
                    .password("password")
                    .roles("USER")
                    .and()
                .withUser("admin") // #2
                    .password("password")
                    .roles("ADMIN","USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        LOG.debug("****************** Inside security configure");
        http
            .authorizeRequests()
            .antMatchers("/signup","/about","/ping","/resources/**").permitAll() // #4
            .antMatchers("/admin/**").hasRole("ADMIN") // #6
            .anyRequest().permitAll() // 7
            .and()
            .formLogin()  // #8
            .loginPage("/login").permitAll(); // #5
    }

    @Bean
    public JWTTokenAuthFilter jwtTokenAuthFilter(){
        return new JWTTokenAuthFilter();
    }
}
