package org.sam.api.handler;

import org.sam.api.domain.LoginUser;
import org.sam.api.domain.Member;
import org.sam.api.domain.Post;
import org.sam.api.payload.PostDto;
import org.sam.api.service.MemberService;
import org.sam.api.service.PostService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.*;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.Session;
import org.sam.server.http.web.response.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/07/17
 * Time: 2:33 PM
 */
@Handler("/post")
public class PostHandler {

    private final PostService postService;
    private final MemberService memberService;

    public PostHandler(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @RestApi
    @GetMapping
    public ResponseEntity<?> getPostList() {
        List<PostDto.ListResponse> postList = postService.getPostList();

        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostDetail(@PathValue Long id) {
        PostDto.DetailResponse post = postService.getPostDetail(id);
        if (post == null) return ResponseEntity.notFound(null);

        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostMapping
    public ResponseEntity<Object> registerPost(@JsonRequest Post post, Session session) throws IOException {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
        Member member = memberService.getMemberInfo(loginUser.getId());
        post.setWriter(member);
        Long postId = postService.registerPost(post);

        return ResponseEntity.of(HttpStatus.CREATED, postId);
    }

    @RestApi
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathValue Long id, @JsonRequest PostDto.UpdateRequest request) {
        boolean result = postService.updatePost(id, request);
        if (!result) return ResponseEntity.badRequest(null);
        PostDto.DetailResponse postDetail = postService.getPostDetail(id);

        return ResponseEntity.ok(postDetail);
    }

    @RestApi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathValue Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(null);
    }

}
