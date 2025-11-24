package com.back.domain.post.postChain.entity;

import com.back.domain.post.post.entity.Post;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class PostChainItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private final PostChain postChain;
    @ManyToOne(fetch = LAZY)
    private final Post post;
    private int no;

    public PostChainItem() {
        this.postChain = null;
        this.post = null;
    }

    public PostChainItem(PostChain postChain, Post post, int no) {
        this.postChain = postChain;
        this.post = post;
        this.no = no;
    }
}
