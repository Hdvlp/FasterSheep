package com.fastersheep.fastersheep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AdminRegisterController {
    
    @GetMapping("/adminRegister")
    public String indexPage(HttpServletRequest request) {

        return new String("""
                <!doctype>
                <html><head><meta charset="UTF-8"/>
                <style>body{font-size: 24px; background: #ccbbbc;} input{font-size: 24px;}</style>
                </head>
                <body>
                    <div>Register</div>
                    <div><form action="/adminRegister/create" method="POST"></div>
                    <div>User name: <input name="username" type="text"/></div>
                    <div>Password: <input name="password" type="password"/></div>
                    <div><input name="submit" type="submit" value="Submit"/></div>
                    <div></form></div>
                </body>
                </html>
                """);
    }

}
