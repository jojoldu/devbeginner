package com.blogcode.oauth.domain;

import com.blogcode.member.domain.Member;
import com.blogcode.oauth.pojo.FacebookField;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by jojoldu@gmail.com on 2016-12-22.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Entity
@DiscriminatorValue("FACEBOOK")
public class Facebook extends Member{


    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column
    private String picture;

    public Facebook() {
    }

    public Facebook(String id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Facebook(ObjectMapper objectMapper, Authentication authentication) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Object details = oAuth2Authentication.getUserAuthentication().getDetails();

        @SuppressWarnings("unchecked")
        Map<String, Object> detailsMap = objectMapper.convertValue(details, Map.class);

        this.id = detailsMap.get(FacebookField.ID.getName()).toString();
        this.name = detailsMap.get(FacebookField.NAME.getName()).toString();
        this.email = detailsMap.get(FacebookField.EMAIL.getName()).toString();
        this.picture = extractUrl(detailsMap);

    }


    private String extractUrl (Map<String, Object> userDetails) {
        return Optional.of(new JSONObject(userDetails))
                .map(j -> j.getJSONObject(FacebookField.PICTURE.getName()))
                .map(p -> p.getJSONObject(FacebookField.DATA.getName()))
                .map(d -> d.get(FacebookField.URL.getName()).toString())
                .orElse("");
    }


    public long getIdx() {
        return super.getIdx();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
