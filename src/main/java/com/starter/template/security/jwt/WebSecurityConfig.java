package com.starter.template.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired private UserDetailsService userDetailsService;
	 @Autowired private PasswordEncoder passwordEncoder;
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		    .antMatchers("/","/swagger-resources","/v2/*","/actuator","/actuator/*", "/Swagger-ui.html").permitAll()
			.antMatchers("/demo").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers("/user","/user/**").permitAll()
			.anyRequest().authenticated()
			.and()
			// We filter the api/login requests
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			// And filter other requests to check the presence of JWT in header
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }

	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
	 	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	 } 
}