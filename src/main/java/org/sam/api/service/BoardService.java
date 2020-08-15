package org.sam.api.service;

import org.sam.api.domain.Member;
import org.sam.api.domain.Post;
import org.sam.api.repositoty.BoardRepository;
import org.sam.server.annotation.component.Service;
import org.sam.server.constant.HttpStatus;
import org.sam.server.http.ResponseEntity;
import org.sam.server.http.Session;

import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:34 PM
 */
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Post> getBoardList() {
        return boardRepository.getPostList();
    }

    public ResponseEntity<?> registerPost(Post post, Session session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) return ResponseEntity.of(HttpStatus.UNAUTHORIZED, null);
        post.setWriter(loginUser);
        boardRepository.registerPost(post);
        return ResponseEntity.of(HttpStatus.CREATED, null);
    }
}
