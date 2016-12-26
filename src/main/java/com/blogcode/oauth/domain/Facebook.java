package com.blogcode.oauth.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Map;
import java.util.Optional;

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
        this.id = userDetails.get("id").toString();
        this.name = userDetails.get("name").toString();
        this.email = userDetails.get("email").toString();

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
