package com.fastersheep.fastersheep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {
    
    @GetMapping("/userRegister")
    public String indexPage(HttpServletRequest request) {
        //return new String("Hello here..." + request.getSession().getId());
        return new String("""
                <!doctype>
                <html><head><meta charset="UTF-8"/>
                <style>body{font-size: 24px;} input{font-size: 24px;}</style>
                </head>
                <body>
                    <div>Register</div>
                    <div><form action="/register" method="POST"></div>
                    <div>User name: <input name="username" type="text"/></div>
                    <div>Password: <input name="password" type="password"/></div>
                    <div><input name="submit" type="submit" value="Submit"/></div>
                    <div></form></div>
                </body>
                </html>
                """);
    }

}
