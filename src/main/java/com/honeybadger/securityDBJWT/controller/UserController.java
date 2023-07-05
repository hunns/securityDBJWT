package com.honeybadger.securityDBJWT.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/tea")
    public String getTea(){
        return "Tea";
    }

    @GetMapping("/biscuit")
    public String getBiscuit(){
        return "Biscuit";
    }

    @GetMapping("/samosa")
    public String getSamosa(){
        return "Samosa";
    }

}
