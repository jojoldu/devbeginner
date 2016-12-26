package com.blogcode.oauth.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by jojoldu@gmail.com on 2016-12-25
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
public class FacebookUserDetails implements UserDetails {

    private Facebook facebook;

    public FacebookUserDetails(Facebook facebook) {
        this.facebook = facebook;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.facebook.getEmail();
    }

    public Facebook getFacebook() {
        return this.facebook;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
