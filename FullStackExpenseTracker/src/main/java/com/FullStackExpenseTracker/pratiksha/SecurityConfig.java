package com.FullStackExpenseTracker.pratiksha;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import jakarta.servlet.Filter;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/users/signup", "/api/users/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter((Filter) (authenticationManager()))
            .addFilter((Filter) authenticationManager());
    }

    private Object authenticationManager() {
		// TODO Auto-generated method stub
		return null;
	}

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password("{noop}password")
            .roles("USER");
    }

}
