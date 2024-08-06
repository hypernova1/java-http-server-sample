package org.sam.api.post.ui;

import org.sam.api.auth.domain.LoginUser;
import org.sam.api.common.Page;
import org.sam.api.common.exception.BadRequestException;
import org.sam.api.common.exception.UnauthorizedException;
import org.sam.api.post.application.PostService;
import org.sam.api.post.application.payload.in.UpdatePostDto;
import org.sam.api.post.application.payload.out.PostDetailDto;
import org.sam.api.post.application.payload.out.PostSummaryDto;
import org.sam.api.post.domain.Post;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.*;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.Session;
import org.sam.server.http.web.response.ResponseEntity;

/**
 * Created by melchor
 * Date: 2020/07/17
 * Time: 2:33 PM
 */
@Handler("/post")
public class PostHandler {

    private final PostService postService;

    public PostHandler(PostService postService) {
        this.postService = postService;
    }

    @RestApi
    @GetMapping
    public ResponseEntity<?> getList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<PostSummaryDto> postList = postService.findAll(page, size);
        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathValue Long id) {
        PostDetailDto post = postService.findOne(id);
        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostMapping
    public ResponseEntity<?> save(@JsonRequest Post post, Session session) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new UnauthorizedException();
        }
        Long postId = postService.save(post, loginUser);

        return ResponseEntity.of(HttpStatus.CREATED, postId);
    }

    @RestApi
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathValue Long id, @JsonRequest UpdatePostDto request) {
        boolean result = postService.update(id, request);
        if (!result) {
            throw new BadRequestException();
        }
        PostDetailDto postDetail = postService.findOne(id);
        return ResponseEntity.ok(postDetail);
    }

    @RestApi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathValue Long id) {
        postService.delete(id);
        return ResponseEntity.ok();
    }

}
