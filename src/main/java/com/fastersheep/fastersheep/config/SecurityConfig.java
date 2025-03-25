package com.fastersheep.fastersheep.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChainFetch(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/fetch", "/fetch/save" , "/fetch/**" };
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChainRegister(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/userRegister", "/register", "/error" };
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .build();
    }


    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChainApi(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/", "/api/save" };

        http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(
                allowedPaths
            )
            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
		    .formLogin(form -> form
                .permitAll()
		    )
            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout
                .deleteCookies("JSESSIONID")
            );
 
        return http.build();

    }

   
    @Bean
    @Order(4)
    public SecurityFilterChain securityFilterChainLogin(HttpSecurity http) throws Exception{
        
        String[] allowedPaths = { "/login" , "/**"};
        http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(
                allowedPaths
            )
            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
            .formLogin(
                formLogin ->
                formLogin
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout
                .deleteCookies("JSESSIONID")
            );
            
        return http.build();
    }


    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
