package com.blogcode;

import com.blogcode.member.domain.Member;
import com.blogcode.member.repository.MemberRepository;
import com.blogcode.oauth.domain.Facebook;
import com.blogcode.posting.service.PostingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplicationTests {

	@Autowired
	private PostingService postingService;

	@Autowired
	private MemberRepository<Member> memberRepository;

	@Autowired
	private MemberRepository<Facebook> facebookRepository;

	private Facebook facebook;

	@Before
	public void setup () {
		facebook = new Facebook("이동욱", "jojoldu", "jojoldu@gmail.com", "http://facebook/image");
		memberRepository.save(facebook);
	}


	@Test
	public void test_Posting작성 () {
		String content = "테스트입니다";
		Facebook savedFacebook = facebookRepository.findAll().get(0);
		assertThat(savedFacebook.getId(), is(facebook.getId()));
		postingService.write(content, facebook);

		List<Facebook> facebooks  = facebookRepository.findAll();
		assertThat(facebooks.size(), is(1));
	}



}
