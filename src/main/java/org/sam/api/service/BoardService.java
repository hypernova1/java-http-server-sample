package org.sam.api.service;

import org.sam.api.domain.Post;
import org.sam.api.payload.BoardDto;
import org.sam.api.repositoty.BoardRepository;
import org.sam.server.annotation.component.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<BoardDto.ListResponse> getPostList() {
        List<Post> postList = boardRepository.findAll();
        return postList.stream().map(post -> {
            BoardDto.ListResponse postDto = new BoardDto.ListResponse();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            postDto.setRegDate(post.getRegDate().toString());
            postDto.setWriter(post.getWriter().getName());
            return postDto;
        }).collect(Collectors.toList());
    }

    public Long registerPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        Post savedPost = boardRepository.save(post);
        return savedPost.getId();
    }

    public BoardDto.DetailResponse getPostDetail(Long id) {
        Post savedPost = boardRepository.findById(id);
        BoardDto.DetailResponse detailResponse = new BoardDto.DetailResponse();
        detailResponse.setId(savedPost.getId());
        detailResponse.setTitle(savedPost.getTitle());
        detailResponse.setContent(savedPost.getContent());
        detailResponse.setWriter(savedPost.getWriter().getName());

        return detailResponse;
    }
}
