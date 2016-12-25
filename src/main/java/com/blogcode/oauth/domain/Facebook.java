package com.blogcode.oauth.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

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

    @Column
    @Email
    private String email;
    @Column
    private String picture;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public Facebook(String email, String picture, String firstName, String lastName) {
        this.email = email;
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getIdx() {
        return idx;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
