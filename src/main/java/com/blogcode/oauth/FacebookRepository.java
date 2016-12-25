package com.blogcode.oauth;

import com.blogcode.oauth.domain.Facebook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jojoldu@gmail.com on 2016-12-23.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public interface FacebookRepository extends JpaRepository<Facebook, Long> {
    Facebook findByEmail(String email);
}
