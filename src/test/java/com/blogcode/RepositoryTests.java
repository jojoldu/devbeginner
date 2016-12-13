package com.blogcode;

import com.blogcode.posting.domain.Posting;
import com.blogcode.posting.repository.PostingRepository;
import com.blogcode.reply.domain.Reply;
import com.blogcode.reply.repository.ReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void test_Java8TimeAndJpa () {
        replyRepository.save(new Reply(0, 0, "테스트"));
    }


    @Test
    public void test_replayPaging() {

        for(int i=0;i<10;i++) {
           for(int j=0;j<100;j++){
               replyRepository.save(new Reply(i, i, "테스트 "+i+" 와 "+j));
           }
        }

        PageRequest pageRequest = new PageRequest(0, 30);
        List<Reply> replies = replyRepository.findByPostingIdx(0, pageRequest);
        assertThat(replies.size(), is(30));
        assertThat(replies.get(0).getIdx(), is(1L));

        PageRequest descPageRequest = new PageRequest(1, 30, Sort.Direction.DESC);
        List<Reply> descReplies = replyRepository.findByPostingIdx(0, descPageRequest);
        assertThat(descReplies.get(0).getIdx(), is(60L));
    }
}
