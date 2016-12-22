package com.blogcode.oauth;

/**
 * Created by jojoldu@gmail.com on 2016-12-22.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
public class Facebook {
    private String name;
    private String picture;
    private String firstName;
    private String lastName;

    public Facebook(String name, String picture, String firstName, String lastName) {
        this.name = name;
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
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
