package com.honeybadger.securityDBJWT.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/manager")
@PreAuthorize("hasRole('ADMIN') || hasRole('Manager')")
public class ManagerController {
    @GetMapping()
    @PreAuthorize("hasAuthority('admin:read') || hasAuthority('manager:read')")
    public String getRequest(){
        return "Get Manager called by" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin:create') || hasAuthority('manager:create')")
    public String postRequest(@RequestBody String someValue){
        return "Manager post method called with value ";
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('admin:update') || hasAuthority('manager:update')")
    public String putRequest(@RequestBody String someValue){
        return "Admin put method called with value "+  someValue;
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('admin:delete') || hasAuthority('manager:delete')")
    public String deleteRequest(@RequestBody String someValue){
        return "Admin Delete method called with value "+  someValue;
    }


}
