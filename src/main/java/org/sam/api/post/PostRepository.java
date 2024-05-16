package org.sam.api.post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();

    Post save(Post post);

    Post findById(Long id);

    void delete(Long id);

}
