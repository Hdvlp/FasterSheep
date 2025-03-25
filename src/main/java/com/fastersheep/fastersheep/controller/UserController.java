package com.fastersheep.fastersheep.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastersheep.fastersheep.model.Users;
import com.fastersheep.fastersheep.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    //public Users register(@RequestBody Users user){
    public String register(@RequestParam Map<String, String> body){   
        Users user = new Users(); 
        String tmpUsername = body.get("username").trim();
        String tmpPassword = body.get("password").trim();

        if (tmpUsername.length() > 60)
            return new String("Username is longer than 60 characters.");
        
        if (tmpPassword.length() > 60)
            return new String("Password is longer than 60 characters.");

        String allowStrInUsername = "abcdefghijklmnopqrstuvwxyz1234567890";
        String allowStrInPassword = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_@+=";

        String tmpUsernameSanitized = "";
        String tmpPasswordSanitized = "";

        if(tmpUsername.length() < 3)
            return new String("Error: The number of characters in the username is less than 3.");
        if(tmpPassword.length() < 3)
            return new String("Error: The number of characters in the password is less than 3.");


        for (int i=0; i < tmpUsername.length(); i++){
            if (allowStrInUsername.indexOf(tmpUsername.charAt(i)) > -1) 
                tmpUsernameSanitized += tmpUsername.charAt(i);
        }
        
        if(tmpUsername.length() != tmpUsernameSanitized.length())
            return new String("Error: User name format is not okay. Allowed characters are: " + allowStrInUsername);

        for (int i=0; i < tmpPassword.length(); i++){
            if (allowStrInPassword.indexOf(tmpPassword.charAt(i)) > -1) 
                tmpPasswordSanitized += tmpPassword.charAt(i);
        }
        if(tmpPassword.length() != tmpPasswordSanitized.length())
            return new String("Error: Password format is not okay. Allowed characters are: " + allowStrInPassword);

        user.setUsername(tmpUsernameSanitized);
        user.setPassword(tmpPasswordSanitized);
        return service.register(user);
    }
    

}
