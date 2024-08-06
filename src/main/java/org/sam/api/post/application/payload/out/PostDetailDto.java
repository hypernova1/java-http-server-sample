package org.sam.api.post.application.payload.out;

import org.sam.api.post.domain.Post;

import java.text.SimpleDateFormat;

public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String createdAt;

    public PostDetailDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = new SimpleDateFormat("yyyy-MM-dd").format(post.getCreatedAt());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
