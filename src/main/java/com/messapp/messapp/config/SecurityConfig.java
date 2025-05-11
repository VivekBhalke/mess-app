package com.messapp.messapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.messapp.messapp.exception.CustomAuthenticationFailureHandler;
import com.messapp.messapp.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationEntryPoint jwtEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    System.out.println("REACHED SECURITY FILTER CHAIN");

	    return http
	        .csrf(customizer -> customizer.disable())  // Disable CSRF (for stateless apps)
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/user/login" , "/user/register/person" , "/user/register/admin").permitAll()  // Allow open access to login and register
	            .anyRequest().authenticated())  // Require authentication for all other requests // Custom handler for authentication failures
	        .httpBasic(Customizer.withDefaults())  // Use HTTP basic authentication (or you can change this if you’re using JWT)
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .exceptionHandling( exception -> exception.authenticationEntryPoint(jwtEntryPoint))
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Add JWT filter before UsernamePasswordAuthenticationFilter
	        .build();
	}
	

	
	
	//these below classes are used to tell spring how to do the authentication
	//and the above filter chain is used to tell the spring that this is the sequence for the middlewares (filters)
	
	
	//by default spring security uses its own authentication provider so we said that 
	//we are providing our own authentication provider
	//which is the Dao (database) authentication provider
	//to that we are specifying our password encoder (bcrypt)
	//and the userDetailsService object
	@Bean
	public AuthenticationProvider authenticationProvider()  throws Exception
	{ 
		try {
			System.out.println("REACHED THE AUTHENTICATION PROVIDER");
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
			provider.setUserDetailsService(userDetailsService);
			return  provider;
		}
		catch(Exception e)
		{
			System.out.println("in the authentication provider");
			System.out.println(e);
			throw e;
		}
	}
	
	
	//if we do not define this then the spring would use its own authentication manager
	//which uses its own authentication provider
	//but since we have defined it then we have to use our own authentication manager
	//and where to use it and how to use it is totally our concern
	//authentication manager internally uses authentication provider
	//authentication provider tells that i want dao authentication provider
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		System.out.println("REACHED THE AUTHENTICATION manager");
		return config.getAuthenticationManager();
	}

}
