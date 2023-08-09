package com.users.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService);
	}

	
	@Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {
		  http.csrf()
          .disable()
          .authorizeRequests()
          //.antMatchers("/api/rest/**").permitAll()
          .antMatchers("/", "/index.html", "/static/**","/*.ico", "/*.json", "/*.png", "/api/rest/**").permitAll()
          .anyRequest()
          .authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  
		  http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
}