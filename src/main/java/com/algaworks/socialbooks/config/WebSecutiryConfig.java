package com.algaworks.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("algaworks").password("senha").roles("USER");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()//CrossOrigin permite que as chamadas funcionem em multiplos dominios ...javascript
			.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf().disable();
	}
}
