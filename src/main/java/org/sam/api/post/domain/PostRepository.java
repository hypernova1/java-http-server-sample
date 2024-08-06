package org.sam.api.post.domain;

import java.util.List;

public interface PostRepository {

    List<Post> findAll(int page, int size);

    Post save(Post post);

    Post findById(Long id);

    void delete(Long id);

    int countAll();
}
