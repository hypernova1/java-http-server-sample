package org.sam.api.post;

import org.sam.api.member.Member;

import java.time.LocalDateTime;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:32 PM
 */
public class Post {
    private Long id;
    private String title;
    private String content;
    private Member writer;
    private LocalDateTime regDate;

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

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void update(PostDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
