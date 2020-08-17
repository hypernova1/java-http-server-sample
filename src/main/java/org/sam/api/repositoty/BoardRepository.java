package org.sam.api.repositoty;

import org.sam.api.domain.Post;
import org.sam.server.annotation.component.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:33 PM
 */
@Repository
public class BoardRepository {

    private Long id = 1L;
    List<Post> postList = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setId(id++);
            post.setTitle("Sample Post" + i);
            post.setContent("Sample 입니다.");
            postList.add(post);
        }
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post save(Post post) {
        post.setId(id++);
        this.postList.add(post);
        return post;
    }

    public Post findById(Long id) {
        return postList.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }
}
