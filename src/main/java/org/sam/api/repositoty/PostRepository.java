package org.sam.api.repositoty;

import org.sam.api.domain.Member;
import org.sam.api.domain.Post;
import org.sam.server.annotation.component.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:33 PM
 */
@Repository
public class PostRepository {

    private Long id = 1L;
    List<Post> postList = new ArrayList<>();

    {
        Member member = new Member();
        member.setEmail("tester@test.com");
        member.setName("tester");
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setId(id++);
            post.setTitle("Sample Post" + i);
            post.setWriter(member);
            post.setContent("Sample Content.");
            post.setRegDate(LocalDateTime.now());
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

    public void delete(Long id) {
        postList.removeIf(post -> post.getId().equals(id));
    }
}
