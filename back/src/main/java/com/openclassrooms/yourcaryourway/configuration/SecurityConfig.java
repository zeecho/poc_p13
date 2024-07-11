package com.openclassrooms.yourcaryourway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	        http
            .csrf().disable()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/login", "/register").permitAll()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login").permitAll()
            )
            .logout(logout ->
                logout.permitAll()
            );
	  }
	  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
