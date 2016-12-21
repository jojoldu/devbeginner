package com.blogcode.ui;

import org.springframework.stereotype.Controller;
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
}
