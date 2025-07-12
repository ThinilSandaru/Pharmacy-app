package com.example.Pharmacy.Config;


import com.example.Pharmacy.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class Security {
	private final CustomUserDetailsService customUserDetailsService;

	public Security(CustomUserDetailsService customUserDetailsService){
		System.out.println("hello world");
		this.customUserDetailsService=customUserDetailsService;
	}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.cors(Customizer.withDefaults())
		.authorizeHttpRequests(auth -> auth
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.requestMatchers("/pharmacy/**").hasRole("PHARMACY")
			.requestMatchers("/public/**").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
	return http.build();
}

@Bean
public DaoAuthenticationProvider daoAuthenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(customUserDetailsService);
		return provider;
}
@Bean
public CorsConfigurationSource corsConfigurationSource(){

	CorsConfiguration config = new CorsConfiguration();
	config.setAllowedOrigins(List.of("http://localhost:3000"));
	config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	config.setAllowedHeaders(List.of("*"));
	config.setAllowCredentials(true);
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", config);
	return source;
}


@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return new ProviderManager(daoAuthenticationProvider());
}
}
