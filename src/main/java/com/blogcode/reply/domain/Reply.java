package com.blogcode.reply.domain;

import com.blogcode.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by jojoldu@gmail.com on 2016-12-12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @Column
    private long postingIdx;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_member"))
    private Member member;

    @Column
    private String content;

    @Column
    private LocalDateTime updateDate;

    public Reply() {
    }

    public Reply(long postingIdx, Member member, String content) {
        this.postingIdx = postingIdx;
        this.member = member;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

    public long getIdx() {
        return idx;
    }

    public long getPostingIdx() {
        return postingIdx;
    }

    public Member getMember() {
        return member;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}
