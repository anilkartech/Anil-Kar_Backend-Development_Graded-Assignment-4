package com.springRestApi.EmployeeManagementRestApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.springRestApi.EmployeeManagementRestApi.service.DomainUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DomainUserDetailsService domainUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser(User.withUsername("Anil").password(getPasswordEncoder().encode("welcome")).roles("USER"))
//		.withUser(User.withUsername("Somya").password(getPasswordEncoder().encode("welcome")).roles("ADMIN"));
		auth.userDetailsService(domainUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
				.antMatchers("/api/employees/**").hasAnyAuthority("USER", "ADMIN").anyRequest().authenticated().and()
				.httpBasic();
	}

}
