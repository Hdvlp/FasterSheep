package com.fastersheep.fastersheep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/dbCredentials")

// FAQ: 
// For changing the database username and password cached
// in this application without restarting this application.

public class ActuatorController {
    @Autowired
    private DriverManagerDataSource dataSource;

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam String username, 
        @RequestParam String password,
        SecurityContextHolderAwareRequestWrapper requestWrapper) {

            
            // Dynamically update username and password
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return "Database credentials updated successfully.";
    }
}
