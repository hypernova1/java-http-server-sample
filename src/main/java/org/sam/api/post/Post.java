package org.sam.api.post;

import org.sam.api.member.Member;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:32 PM
 */
public class Post {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private Member member;
    private Date createdAt;
    private Date updatedAt;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void update(PostDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", memberId=" + memberId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
