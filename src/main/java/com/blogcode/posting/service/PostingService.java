package com.blogcode.posting.service;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.posting.domain.Posting;
import com.blogcode.posting.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jojoldu@gmail.com on 2016-12-13
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Service
@Transactional
public class PostingService {

    @Autowired
    private PostingRepository postingRepository;

    public void write(String content, Member member) {
        Posting posting = new Posting(member, content);
        postingRepository.save(posting);
        member.writePosting(posting);
    }
}
