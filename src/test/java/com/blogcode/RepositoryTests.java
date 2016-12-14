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
import java.util.stream.Stream;

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
        Stream<Reply> replies = replyRepository.findByPostingIdx(0, pageRequest);
        assertThat(replies.count(), is(30L));
        assertThat(replies.findFirst().get().getIdx(), is(1L));

        PageRequest descPageRequest = new PageRequest(1, 30, Sort.Direction.ASC, "updateDate");
        Stream<Reply> descReplies = replyRepository.findByPostingIdx(0, descPageRequest);
        assertThat(descReplies.findFirst().get().getIdx(), is(31L));
        assertThat(descReplies.findAny().get().getIdx(), is(40L));

        PageRequest descPageRequest2 = new PageRequest(1, 30, Sort.Direction.DESC, "updateDate");
        Stream<Reply> descReplies2 = replyRepository.findByPostingIdx(0, descPageRequest2);
        assertThat(descReplies2.get(0).getIdx(), is(70L));
        assertThat(descReplies2.get(29).getIdx(), is(40L));


    }
}
