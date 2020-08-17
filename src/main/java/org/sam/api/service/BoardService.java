package org.sam.api.service;

import org.sam.api.domain.Post;
import org.sam.api.repositoty.BoardRepository;
import org.sam.server.annotation.component.Service;

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

    public List<Post> getPostList() {
        return boardRepository.findAll();
    }

    public Long registerPost(Post post) {
        Post savedPost = boardRepository.save(post);
        return savedPost.getId();
    }

    public Post getPostDetail(Long id) {
        return boardRepository.findById(id);
    }
}
