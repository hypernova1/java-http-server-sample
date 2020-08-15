package org.sam.api.service;

import org.sam.api.domain.Post;
import org.sam.api.repositoty.BoardDao;
import org.sam.server.annotation.component.Service;

import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:34 PM
 */
@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public List<Post> getBoardList() {
        return boardDao.getPostList();
    }

//    public void registerPost(Post post) {
//        boardDao.insertPost(post);
//    }
}
