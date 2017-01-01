package com.blogcode.member.domain;

import com.blogcode.posting.domain.Posting;
import com.blogcode.reply.domain.Reply;
import javafx.geometry.Pos;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jojoldu@gmail.com on 2016-12-12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idx;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reply> replies;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Posting> postings;

    public long getIdx() {
        return idx;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public Set<Posting> getPostings() {
        return postings;
    }

    public void writePosting(Posting posting){
       this.postings.add(posting);
    }
}
