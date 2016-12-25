package com.blogcode.oauth;

import com.blogcode.oauth.domain.Facebook;
import com.blogcode.oauth.domain.FacebookUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by jojoldu@gmail.com on 2016-12-25
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Service
public class FacebookDetailsService implements UserDetailsService {

    @Autowired
    private FacebookRepository facebookRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Facebook facebook = facebookRepository.findByEmail(email);
        if(facebook == null){
            throw new UsernameNotFoundException("Not Found User");
        }

        return new FacebookUserDetails(facebook);
    }
}
