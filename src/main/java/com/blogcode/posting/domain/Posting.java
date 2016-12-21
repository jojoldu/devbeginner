package com.blogcode.posting.domain;

import com.blogcode.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_posting_member"))
    private Member member;

    @Column
    private LocalDateTime updateDate;

    public Posting(Member member, String content) {
        this.member = member;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

    public long getIdx() {
        return idx;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public void edit(String content){
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}
