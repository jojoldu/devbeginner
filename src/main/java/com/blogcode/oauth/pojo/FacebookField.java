package com.blogcode.oauth.pojo;

/**
 * Created by jojoldu@gmail.com on 2016-12-25
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public enum FacebookField {
    ID("id"),
    NAME("name"),
    EMAIL("email"),
    PICTURE("picture"),
    DATA("data"),
    URL("url"),
    LINK("link");

    private String name;

    FacebookField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
