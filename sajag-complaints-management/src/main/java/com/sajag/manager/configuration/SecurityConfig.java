package com.sajag.manager.configuration;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sajag.manager.configuration.security.UserTokenEntrypoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
	  http.authorizeRequests()
		.antMatchers("/api/request/**").access("hasRole('ADMIN')")
		.antMatchers("/allSubCategories/**").access("hasRole('ADMIN') or hasRole('DBA')")
		.and().formLogin();
		
	}
	
	/** Token based starts here */
	@Bean
	public UserTokenEntrypoint userTokenAuthenticationEntryPoint() {
		return new UserTokenEntrypoint();
	}

	/*@Bean
	public AuthenticationProvider userTokenAuthenticationProvider() {
		return new UserTokenAuthenticationProvider();
	}

	@Bean
	public ClearSessionFilter clearSessionFilter() {
		return new ClearSessionFilter();
	}

	@Bean
	public UserTokenFilter userTokenFilter() {
		UserTokenFilter userTokenFilter = new UserTokenFilter();
		userTokenFilter.setAuthenticationManager(authenticationManager());
		userTokenFilter
				.setAuthenticationSuccessHandler(tokenAuthenticationSuccessHandler);
		return userTokenFilter;
	}
	
	@Bean
	public DelegatingAuthenticationEntryPoint authEntryPoint() {
		LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<RequestMatcher, AuthenticationEntryPoint>();
		entryPoints.put(new RequestHeaderRequestMatcher("usertoken"),
				userTokenAuthenticationEntryPoint());
		DelegatingAuthenticationEntryPoint authEntryPoint = new DelegatingAuthenticationEntryPoint(
				entryPoints);
		authEntryPoint
				.setDefaultEntryPoint(new UsernamePasswordAuthenticationEntryPoint());
		return authEntryPoint;
	}*/
}
