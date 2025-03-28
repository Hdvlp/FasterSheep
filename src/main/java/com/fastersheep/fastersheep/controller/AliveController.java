package com.fastersheep.fastersheep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")

public class AliveController {


    @GetMapping("/alive")
    public String alive() {
        return new String("""
            <div>Alive</div>
                
                """);
    }
}
