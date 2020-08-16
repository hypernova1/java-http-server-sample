package org.sam.api.handler;

import org.sam.api.domain.Member;
import org.sam.api.domain.Post;
import org.sam.api.service.BoardService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.*;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.ResponseEntity;
import org.sam.server.http.Session;

import java.io.IOException;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/07/17
 * Time: 2:33 PM
 */
@Handler("/board")
public class BoardHandler {

    private final BoardService boardService;

    public BoardHandler(BoardService boardService) {
        this.boardService = boardService;
    }

    @RestApi
    @GetHandle
    public ResponseEntity<?> getPostList() {
        List<Post> postList = boardService.getPostList();
        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetHandle("/{id}")
    public ResponseEntity<?> getPostDetail(@PathValue Long id) {
        Post post = boardService.getPostDetail(id);
        if (post == null) return ResponseEntity.notFound(null);
        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostHandle
    public ResponseEntity<Object> registerPost(@JsonRequest Post post, Session session) throws IOException {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
        post.setWriter(loginUser);
        boardService.registerPost(post);
        return ResponseEntity.of(HttpStatus.CREATED, null);
    }

}
