package com.blogcode.api;

import org.springframework.security.core.Authentication;
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


    @GetMapping("/user")
    public Principal getUser(Principal principal, Authentication authentication){
        return principal;
    }
}
