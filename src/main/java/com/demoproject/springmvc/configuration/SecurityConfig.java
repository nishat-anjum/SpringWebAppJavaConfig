package com.demoproject.springmvc.configuration;

import com.demoproject.springmvc.service.LogInService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* Created by nishat on 10/26/15.
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(SecurityConfig.class);


    @Autowired
    private LogInService logInService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(logInService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.debug("------------------------- initialized security -------------------------");

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll().and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login" )
                .defaultSuccessUrl("/profile")
                .failureUrl("/login?error=1")
                .usernameParameter("userEmail")
                .passwordParameter("userPassword").and()
                .logout()
                .invalidateHttpSession(false)
                .logoutUrl("/logout");
    }
}
