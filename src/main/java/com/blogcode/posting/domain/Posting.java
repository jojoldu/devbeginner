package com.blogcode.posting.domain;

import com.blogcode.reply.domain.Reply;

import javax.persistence.*;

/**
 * Created by jojoldu@gmail.com on 2016-12-12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;


}
