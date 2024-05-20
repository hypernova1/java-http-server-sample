package org.sam.api.post;

import org.sam.api.auth.LoginUser;
import org.sam.api.common.Page;
import org.sam.api.exception.BadRequestException;
import org.sam.api.exception.UnauthorizedException;
import org.sam.api.member.MemberService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.*;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.Session;
import org.sam.server.http.web.response.HttpResponse;
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

    public PostHandler(PostService postService) {
        this.postService = postService;
    }

    @RestApi
    @GetMapping
    public ResponseEntity<?> getList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<PostDto.ListResponse> postList = postService.findAll(page, size);
        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathValue Long id) {
        PostDto.DetailResponse post = postService.findOne(id);
        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostMapping
    public ResponseEntity<?> save(@JsonRequest Post post, Session session) throws IOException {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new UnauthorizedException();
        }
        Long postId = postService.save(post, loginUser);

        return ResponseEntity.of(HttpStatus.CREATED, postId);
    }

    @RestApi
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathValue Long id, @JsonRequest PostDto.UpdateRequest request) {
        boolean result = postService.update(id, request);
        if (!result) {
            throw new BadRequestException();
        }
        PostDto.DetailResponse postDetail = postService.findOne(id);
        return ResponseEntity.ok(postDetail);
    }

    @RestApi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathValue Long id) {
        postService.delete(id);
        return ResponseEntity.ok();
    }

}
