package com.blogcode.posting.domain;

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

    @Column
    private LocalDateTime updateDate;

    public Posting(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }

    public long getIdx() {
        return idx;
    }

    public String getContent() {
        return content;
    }
}
