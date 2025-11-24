package com.back.domain.post.postChain.entity;

import com.back.domain.global.exceptions.BusinessException;
import com.back.domain.member.member.entity.Member;
import com.back.domain.post.post.entity.Post;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

@Entity
public class PostChain extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private final Member author;
    @OneToMany(mappedBy = "postChain", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private final List<PostChainItem> items = new ArrayList<>();
    private final boolean isPureAuthor;

    public PostChain() {
        this.author = null;
        this.isPureAuthor = false;
    }

    public PostChain(Member author, boolean isPureAuthor, List<Post> posts) {
        this.author = author;
        this.isPureAuthor = isPureAuthor;

        add(posts);
    }

    public void add(List<Post> posts) {
        posts
                .stream()
                .forEach(post -> {
                    if (isPureAuthor && !author.equals(post.getAuthor())) {
                        throw new BusinessException("400-1", "이 시리즈는 순수하게 한 작가의 글로만 구성될 수 있도록 설정되어 있습니다.");
                    }

                    items.add(
                            new PostChainItem(this, post, items.size() + 1)
                    );
                });
    }
}
