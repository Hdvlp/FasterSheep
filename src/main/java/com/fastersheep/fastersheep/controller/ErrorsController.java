package com.fastersheep.fastersheep.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorsController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return new String("""
            <div>This is an \"Error\" page.</div>
            <div>FAQ: Please try again later, or if you register, try a new account with a new user name,</div>
            <div>or if you log in, check your user name and password.</div>
                
                """);
    }


}


