package com.in28minutes.springboot.mywebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
		.formLogin(
				formLogin->formLogin.successHandler(customAuthenticationSuccessHandler()))
		.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**"))    // Disable CSRF protection for H2 Console)
        .headers(header -> 
        header.frameOptions( frameOptions 
        		-> frameOptions.sameOrigin()));
		return http.build();
	}
	
	@Bean
	public UserDetailsService createUserDetailsManager() {
		Function<String, String> encoderFunction = string -> passwordEncoder().encode(string);
		return new InMemoryUserDetailsManager(
				createUser(encoderFunction, "sadek","sadek","USER","ADMIN"),
				createUser(encoderFunction, "ali","ali","USER"),
				createUser(encoderFunction, "mahdi","mahdi","USER"));
	}

	private UserDetails createUser(Function<String, String> encoderFunction, String username, String password, String... roles) {
		return User.builder()
		.passwordEncoder(encoderFunction)
		.username(username)
		.password(password)
		.roles(roles)
		.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
