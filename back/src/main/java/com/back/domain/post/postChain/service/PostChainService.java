package com.back.domain.post.postChain.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.postChain.entity.PostChain;
import com.back.domain.post.postChain.repository.PostChainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostChainService {
    private final PostChainRepository postChainRepository;

    public PostChain make(Member author, List<Post> posts) {
        PostChain postChain = new PostChain(author, true, posts);

        return postChainRepository.save(postChain);
    }
}
