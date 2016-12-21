package com.blogcode.member.domain;

import com.blogcode.reply.domain.Reply;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jojoldu@gmail.com on 2016-12-12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reply> replies;

    public long getIdx() {
        return idx;
    }

    public Set<Reply> getReplies() {
        return replies;
    }
}
