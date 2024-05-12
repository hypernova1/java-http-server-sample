package org.sam.api.post;

import org.sam.api.auth.LoginUser;
import org.sam.api.member.MemberService;
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
    public ResponseEntity<?> getList() {
        List<PostDto.ListResponse> postList = postService.findAll();

        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathValue Long id) {
        PostDto.DetailResponse post = postService.findOne(id);
        if (post == null) return ResponseEntity.notFound(null);

        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostMapping
    public ResponseEntity<Object> save(@JsonRequest Post post, Session session) throws IOException {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
        Long postId = postService.save(post, loginUser);

        return ResponseEntity.of(HttpStatus.CREATED, postId);
    }

    @RestApi
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathValue Long id, @JsonRequest PostDto.UpdateRequest request) {
        boolean result = postService.update(id, request);
        if (!result) return ResponseEntity.badRequest(null);
        PostDto.DetailResponse postDetail = postService.findOne(id);
        return ResponseEntity.ok(postDetail);
    }

    @RestApi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathValue Long id) {
        postService.delete(id);
        return ResponseEntity.ok(null);
    }

}
