package com.blogcode.oauth.handler;

import com.blogcode.oauth.FacebookRepository;
import com.blogcode.oauth.domain.Facebook;
import com.blogcode.oauth.pojo.FacebookUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2016-12-23
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FacebookRepository facebookRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Facebook requestFacebook = new Facebook(objectMapper, authentication);
        Facebook existingFacebook = facebookRepository.findByEmail(requestFacebook.getEmail());

        if(existingFacebook == null){
            facebookRepository.save(requestFacebook);
        }

        response.sendRedirect("/");
    }
}
