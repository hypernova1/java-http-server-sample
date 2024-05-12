package org.sam.api.post;

import org.sam.api.util.ModelMapper;
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
public class PostService {

    private final PostRepository posts;
    private final ModelMapper modelMapper;

    public PostService(PostRepository posts, ModelMapper modelMapper) {
        this.posts = posts;
        this.modelMapper = modelMapper;
    }

    public List<PostDto.ListResponse> getPostList() {
        List<Post> postList = posts.findAll();
        return postList.stream()
                .map(post -> modelMapper.convert(post, PostDto.ListResponse.class)).collect(Collectors.toList());
    }

    public Long registerPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = posts.save(post);

        return savedPost.getId();
    }

    public PostDto.DetailResponse getPostDetail(Long id) {
        Post savedPost = posts.findById(id);
        if (savedPost == null) return null;
        return modelMapper.convert(savedPost, PostDto.DetailResponse.class);
    }

    public boolean updatePost(Long id, PostDto.UpdateRequest request) {
        Post savedPost = posts.findById(id);
        if (savedPost == null) return false;
        savedPost.update(request);

        return true;
    }

    public void deletePost(Long id) {
        posts.delete(id);
    }
}
