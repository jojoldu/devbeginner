package com.blogcode;

import com.blogcode.reply.domain.Reply;
import com.blogcode.reply.repository.ReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test_Java8TimeAndJpa () {
        replyRepository.save(new Reply(0, "테스트"));
    }
}
