package org.sam.api.post;

import org.sam.api.auth.LoginUser;
import org.sam.api.util.ModelMapper;
import org.sam.server.annotation.component.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:34 PM
 */
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public List<PostDto.ListResponse> getList() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(post -> new PostDto.ListResponse(post)).collect(Collectors.toList());
    }

    public Long save(Post post, LoginUser loginUser) {
        post.setMemberId(loginUser.getId());
        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    public PostDto.DetailResponse getDetail(Long id) {
        Post post = postRepository.findById(id);
        if (post == null) return null;
        return new PostDto.DetailResponse(post);
    }

    public boolean update(Long id, PostDto.UpdateRequest request) {
        Post post = postRepository.findById(id);
        if (post == null) return false;
        post.update(request);

        return true;
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
