package com.blogcode.ui;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jojoldu@gmail.com on 2016-12-21
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/me")
    public Object me(OAuth2Authentication oAuth2Authentication, Model model) {
        if(oAuth2Authentication == null) {
           return "redirect:/";
        }
        model.addAttribute("profile", oAuth2Authentication.getUserAuthentication().getDetails());
        return "me";
    }
}
