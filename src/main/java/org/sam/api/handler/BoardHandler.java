package org.sam.api.handler;

import org.sam.api.domain.LoginUser;
import org.sam.api.domain.Member;
import org.sam.api.domain.Post;
import org.sam.api.payload.BoardDto;
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
    private final MemberService memberService;

    public BoardHandler(BoardService boardService, MemberService memberService) {
        this.boardService = boardService;
        this.memberService = memberService;
    }

    @RestApi
    @GetHandle
    public ResponseEntity<?> getPostList() {
        List<BoardDto.ListResponse> postList = boardService.getPostList();
        return ResponseEntity.ok(postList);
    }

    @RestApi
    @GetHandle("/{id}")
    public ResponseEntity<?> getPostDetail(@PathValue Long id) {
        BoardDto.DetailResponse post = boardService.getPostDetail(id);
        if (post == null) return ResponseEntity.notFound(null);
        return ResponseEntity.ok(post);
    }

    @RestApi
    @PostHandle
    public ResponseEntity<Object> registerPost(@JsonRequest Post post, Session session) throws IOException {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if (loginUser == null) return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
        Member member = memberService.getMemberInfo(loginUser.getId());
        post.setWriter(member);
        Long postId = boardService.registerPost(post);
        return ResponseEntity.of(HttpStatus.CREATED, postId);
    }

}
