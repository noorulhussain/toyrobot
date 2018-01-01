package com.toyrobot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ToyRobotWebSecurity extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/move", "/left", "/right", "/report", "/place", "/reset").permitAll()
                .anyRequest().authenticated()
                .and()
        	.formLogin()
        		.loginPage("/login")
        		.permitAll()
        		.and()
        		.csrf().disable()
        	.logout()
        		.permitAll();
	}
}
