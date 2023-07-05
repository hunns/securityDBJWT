package com.honeybadger.securityDBJWT.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {


    @GetMapping()
    @PreAuthorize("hasAuthority('admin:read')")
    public String getRequest(){
        return "Get Admin called";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:create')")
    public String postRequest(@RequestBody String someValue){
        return "Admin post method called with value "+  someValue;
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('admin:update')")
    public String putRequest(@RequestBody String someValue){
        return "Admin put method called with value "+  someValue;
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteRequest(@RequestBody String someValue){
        return "Admin Delete method called with value "+  someValue;
    }

}
