package com.blogcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by jojoldu@gmail.com on 2016-12-21
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@RestController
public class ApiController {

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/user")
    public UserDetails getUser(Principal principal){
        return userDetailsService.loadUserByUsername(principal.getName());
    }
}
