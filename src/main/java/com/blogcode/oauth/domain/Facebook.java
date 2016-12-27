package com.blogcode.oauth.domain;

import com.blogcode.oauth.pojo.FacebookField;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

/**
 * Created by jojoldu@gmail.com on 2016-12-22.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Entity
public class Facebook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column
    private String picture;

    public Facebook(Map<String, Object> userDetails) {
        this.id = userDetails.get(FacebookField.ID.getName()).toString();
        this.name = userDetails.get(FacebookField.NAME.getName()).toString();
        this.email = userDetails.get(FacebookField.EMAIL.getName()).toString();
        this.picture = extractUrl(userDetails);
    }

    private String extractUrl (Map<String, Object> userDetails) {
        return Optional.of(new JSONObject(userDetails))
                .map(j -> j.getJSONObject(FacebookField.PICTURE.getName()))
                .map(p -> p.getJSONObject(FacebookField.DATA.getName()))
                .map(d -> d.get(FacebookField.URL.getName()).toString())
                .orElse("");
    }

    public Facebook(String id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public long getIdx() {
        return idx;
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
