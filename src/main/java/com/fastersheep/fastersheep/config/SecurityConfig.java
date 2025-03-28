package com.fastersheep.fastersheep.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Value("${allowUserToRegisterAdmin}")
    private String allowUserToRegisterAdmin;

    SecurityConfig(){}

    @Bean
    @Order(100)
    public SecurityFilterChain securityFilterChainAlive(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/alive", "/alive/**" };
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .authorizeHttpRequests(request -> {

                request
                .requestMatchers("/alive/alive")
                .hasAnyRole("GUEST", "USER")
                .anyRequest()
                .authenticated();

            })
            .build();
    }



    @Bean
    @ConditionalOnProperty(name = "allowUserToRegisterAdmin", havingValue = "false", matchIfMissing = false)
    @Order(200)
    public SecurityFilterChain securityFilterChainAdminRegister(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/adminRegister" , "/adminRegister/create"};
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .authorizeHttpRequests(request -> {

                request
                .requestMatchers("/adminRegister").denyAll();


            })
            .build();
    }

    @Bean
    @Order(300)
    public SecurityFilterChain securityFilterChainDbCredentials(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/actuator/dbCredentials/**" };

        // FAQ:
        // This allows you to update the credentials (database username and database password) 
        // without restarting this Spring Boot application.
        // 
        // If you have two database usernames, switch to the second usable username.
        // Both usernames carries the same privileges, e.g. insert, select, update, etc.
        //
        // Then, modify and adjust the command:
        //     dbAdminUsername is the username used in this application with the role DBADMIN.
        //     dbAdminPassword is the password used in this application.
        //     xuser is the username used in the database.
        //     xpass is the password used in the database.
        //     localhost can be the hostname or IP address.
        //     8080 is the default port used.
        //     To make this work for you,
        //     please use the usernames and passwords you actually use in your application and database.
        //
        // curl -X POST -u dbAdminUsername:dbAdminPassword -d "username=xuser&password=xpass" http://localhost:8080/actuator/dbCredentials/updatePassword
        //
        // After running the curl command, you change another pair of username / password of the database. 
        // 
        // Later, you adjust the curl command to change the database credentials again with the first username.
        // This ensures that you are changing database usernames and passwords, and at the same time,
        // you are not restarting the application.
        //
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .authorizeHttpRequests(request -> {

                request
                .requestMatchers("/actuator/dbCredentials/**")
                .hasRole("DBADMIN")
                .anyRequest()
                .authenticated();

            })
            .httpBasic(Customizer.withDefaults())
            .build();
    }


    @Bean
    @Order(400)
    public SecurityFilterChain securityFilterChainFetch(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/fetch", "/fetch/save" , "/fetch/**" };
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .authorizeHttpRequests(request -> {

                request
                .requestMatchers(allowedPaths)
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();

            })
            .httpBasic(Customizer.withDefaults())
            .build();
    }

    @Bean
    @Order(500)
    public SecurityFilterChain securityFilterChainRegister(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/userRegister", "/register", "/error" };
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .build();
    }


    @Bean
    @Order(600)
    public SecurityFilterChain securityFilterChainApi(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/" };
        
        http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(
                allowedPaths
            )
            .authorizeHttpRequests(request -> {
                request.anyRequest().authenticated();
            })
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
    @Order(700)
    public SecurityFilterChain securityFilterChainAdminRegisterAllow(HttpSecurity http) throws Exception{
        String[] allowedPaths = { "/adminRegister" , "/adminRegister/create"};
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(allowedPaths)
            .authorizeHttpRequests(request -> {

                request
                .requestMatchers(allowedPaths)
                .hasRole("USER")
                .anyRequest()
                .authenticated();

            })
            .build();
    }


   
    @Bean
    @Order(800)
    public SecurityFilterChain securityFilterChainLogin(HttpSecurity http) throws Exception{
        
        String[] allowedPaths = { "/login", "/**" };
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
