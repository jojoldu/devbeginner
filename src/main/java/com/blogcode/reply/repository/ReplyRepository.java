package com.blogcode.reply.repository;

import com.blogcode.reply.domain.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2016-12-13
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>{
    List<Reply> findByPostingIdx(long postingIdx, Pageable pageable);
}
