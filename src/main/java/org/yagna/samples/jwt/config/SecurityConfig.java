package org.yagna.samples.jwt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yagna.samples.jwt.filter.StatelessAuthenticationFilter;
import org.yagna.samples.jwt.service.TokenAuthenticationService;
import org.yagna.samples.jwt.service.UserService;


/**
 * Created by asish on 3/28/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public SecurityConfig() {
        super(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        LOG.debug("****************** Inside security configure");

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

}
