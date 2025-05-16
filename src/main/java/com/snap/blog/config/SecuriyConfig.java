package com.snap.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.snap.blog.jwt.JwtRequestFilter;
import com.snap.blog.service.MyUserDetailService;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecuriyConfig {
	
	@Autowired
	private MyUserDetailService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	/**
	 * Method to customize the HTTP default form based authentication. It will help us to secure our API while firing from POSTMAN.
	 * @param httpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(customizer -> customizer.disable()) // Disables the CSRF by creating new session id every time
			.authorizeHttpRequests(request -> {
				request.requestMatchers("api/users/login").permitAll();
				request.requestMatchers(HttpMethod.GET, "/**").permitAll();
				request.anyRequest().authenticated();
			})
			 // authenticate all the request
			.authenticationProvider(authenticationProvider())
//			.httpBasic(Customizer.withDefaults()) // Use to enable authorization through Rest API (Postman)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Make the server stateless so that the previous information are removed
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
		
//		httpSecurity.formLogin(Customizer.withDefaults());
		
	}
	

	/**
	 * Authenticating the user from database
	 * @return
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}
	
//	@Bean
//	public UserDetailsService getUserDetails() {
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username("pratyush").password("P2551969").roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(userDetails);
//	}

}
