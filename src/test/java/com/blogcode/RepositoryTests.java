package com.blogcode;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.oauth.domain.Facebook;
import com.blogcode.posting.domain.Posting;
import com.blogcode.posting.repository.PostingRepository;
import com.blogcode.reply.domain.Reply;
import com.blogcode.reply.repository.ReplyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2016-12-13
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private MemberRepository<Member> memberRepository;

    @Autowired
    private MemberRepository<Facebook> facebookRepository;

    private Facebook facebook;

    @Before
    public void setup () {
        facebook = new Facebook("이동욱", "jojoldu", "jojoldu@gmail.com", "http://facebook/image");
    }

    @Test
    public void test_Java8TimeAndJpa () {
        replyRepository.save(new Reply(0, facebook, "테스트"));
    }


    @Test
    public void test_ReplayPaging() {

        for(int i=0;i<10;i++) {
           for(int j=0;j<100;j++){
               replyRepository.save(new Reply(i, facebook, "테스트 "+i+" 와 "+j));
           }
        }

        PageRequest pageRequest = new PageRequest(0, 30);
        List<Reply> replies = replyRepository.findByPostingIdx(0, pageRequest).collect(Collectors.toList());
        assertThat(replies.get(0).getIdx(), is(1L));
        assertThat(replies.get(29).getIdx(), is(30L));
        assertThat(replies.size(), is(30));

        PageRequest descPageRequest = new PageRequest(1, 30, Sort.Direction.ASC, "idx");
        List<Reply> descReplies = replyRepository.findByPostingIdx(0, descPageRequest).collect(Collectors.toList());
        assertThat(descReplies.get(0).getIdx(), is(31L));
        assertThat(descReplies.get(29).getIdx(), is(60L));

        PageRequest descPageRequest2 = new PageRequest(1, 30, Sort.Direction.DESC, "idx");
        List<Reply> descReplies2 = replyRepository.findByPostingIdx(0, descPageRequest2).collect(Collectors.toList());
        assertThat(descReplies2.get(0).getIdx(), is(70L));
        assertThat(descReplies2.get(29).getIdx(), is(41L));
    }


    @Test
    public void test_Reply와Member관계() {
        Member member = memberRepository.save(facebook);
        postingRepository.save(new Posting(member, "테스트입니다."));

        Posting posting = postingRepository.findAll().get(0);
        replyRepository.save(new Reply(posting.getIdx(), member, "댓글입니다."));

        Reply reply = replyRepository.findAll().get(0);
        Member author = memberRepository.findAll().get(0);

        assertThat(reply.getMember().getIdx(), is(author.getIdx()));
    }

    @Test
    public void test_Facebook과Member관계 () {

        Posting posting = new Posting(facebook, "facebook");
        postingRepository.save(posting);

        Member member = postingRepository.findAll().get(0).getMember();
        Facebook facebook = facebookRepository.findAll().get(0);
        assertThat(postingRepository.findAll().get(0).getMember().getIdx(), is(1L));
    }
}
