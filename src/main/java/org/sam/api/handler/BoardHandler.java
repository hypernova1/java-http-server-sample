package org.sam.api.handler;

import org.sam.api.domain.Post;
import org.sam.api.service.BoardService;
import org.sam.server.annotation.component.Handler;
import org.sam.server.annotation.handle.GetHandle;
import org.sam.server.annotation.handle.JsonRequest;
import org.sam.server.annotation.handle.PostHandle;
import org.sam.server.annotation.handle.RestApi;
import org.sam.server.http.ResponseEntity;

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
        List<Post> postList = boardService.getBoardList();
        return ResponseEntity.ok(postList);
    }

//    @RestApi
//    @PostHandle
//    public ResponseEntity<Object> registerPost(@JsonRequest Post post) throws IOException {
//        boardService.registerPost(post);
//        return ResponseEntity.ok(null);
//    }

}
