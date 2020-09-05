package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder)
		;
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/welcome","/register","/create","/save").permitAll()
		.antMatchers("/adm").hasAuthority("ADMIN")
		.antMatchers("/std").hasAnyAuthority("STUDENT","ADMIN")
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		.anyRequest().authenticated()//remaining url after login valid
		
		//form login
		.and()
		.formLogin()
		.defaultSuccessUrl("/common",true)
		
		//logout 
		.and()
		.logout()
		.logoutRequestMatcher( new AntPathRequestMatcher("/signout"))
		
		
		//wrong url 
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		;
		
	}

}
