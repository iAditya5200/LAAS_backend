package com.victor.laas.configs;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.victor.laas.bo.UserDetailsServiceImpln;
import com.victor.laas.filter.JwtFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
	
	@Autowired
	 JwtFilter jwtFliter;
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          
		return http.
	        		authorizeHttpRequests(request -> request
	        		.requestMatchers("/laas/registerUser", "/laas/addAdmin", "/laas/addStudent", "/laas/login").permitAll()	        		.anyRequest().authenticated())
	                .httpBasic(Customizer.withDefaults())
	                .csrf(AbstractHttpConfigurer::disable)
	                .addFilterBefore(jwtFliter, UsernamePasswordAuthenticationFilter.class)
	                .build();
	    }


	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    }
	    @Autowired
	    private UserDetailsServiceImpln userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	        provider.setUserDetailsService(userDetailsService);


	        return provider;
	    }
	    @Bean
	    public AuthenticationManager authManager( AuthenticationConfiguration config) throws Exception {
                    return config.getAuthenticationManager() ;	    
	    }
}
