package org.sam.api.repositoty;

import org.sam.api.domain.Post;
import org.sam.server.annotation.component.Component;
import org.sam.server.annotation.component.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:33 PM
 */
@Repository
public class BoardRepository {

    List<Post> postList = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setId(i + 1L);
            post.setTitle("Sample Post" + i);
            post.setContent("Sample 입니다.");
            postList.add(post);
        }
    }

    public List<Post> findAll() {
        return postList;
    }

    public void registerPost(Post post) {
        this.postList.add(post);
    }

    public Post findById(Long id) {
        return postList.stream().filter(post -> post.getId().equals(id)).findFirst().get();
    }
}
