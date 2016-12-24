package com.blogcode.config.handler;

import com.blogcode.oauth.FacebookUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by jojoldu@gmail.com on 2016-12-23
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //implementation
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication.getPrincipal();
        FacebookUser facebookUserUser = (FacebookUser) oAuth2Authentication.getDetails();
        System.out.println();
    }
}
