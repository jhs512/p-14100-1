package com.back.global.initData;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import com.back.domain.post.postChain.entity.PostChain;
import com.back.domain.post.postChain.service.PostChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    @Autowired
    @Lazy
    private BaseInitData self;
    private final MemberService memberService;
    private final PostService postService;
    private final PostChainService postChainService;

    @Bean
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
            self.work2();
            self.work3();
        };
    }

    @Transactional
    public void work1() {
        if (memberService.count() > 0) return;

        Member memberSystem = memberService.join("system", "1234", "시스템");
        Member memberAdmin = memberService.join("admin", "1234", "admin");
        Member memberUser1 = memberService.join("user1", "1234", "유저1");
        Member memberUser2 = memberService.join("user2", "1234", "admin");
        Member memberUser3 = memberService.join("user3", "1234", "admin");
    }

    @Transactional
    public void work2() {
        Member memberUser1 = memberService.findByUsername("user1").get();

        Post post1 = postService.write(memberUser1, "제목1", "내용1");
        Post post2 = postService.write(memberUser1, "제목2", "내용2");
        Post post3 = postService.write(memberUser1, "제목3", "내용3");

        PostChain postChain1 = postChainService.make(memberUser1, List.of(post1, post2, post3));
    }

    @Transactional
    public void work3() {
    }
}
