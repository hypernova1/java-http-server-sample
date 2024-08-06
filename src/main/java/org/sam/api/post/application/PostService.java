package org.sam.api.post.application;

import org.sam.api.auth.domain.LoginUser;
import org.sam.api.common.Page;
import org.sam.api.member.domain.Member;
import org.sam.api.member.domain.MemberRepository;
import org.sam.api.post.application.payload.in.UpdatePostDto;
import org.sam.api.post.application.payload.out.PostDetailDto;
import org.sam.api.post.application.payload.out.PostSummaryDto;
import org.sam.api.post.domain.PostNotFoundException;
import org.sam.api.post.domain.Post;
import org.sam.api.post.domain.PostRepository;
import org.sam.server.annotation.component.Service;

import java.util.ArrayList;
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
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Page<PostSummaryDto> findAll(int page, int size) {
        List<Post> posts = postRepository.findAll(page, size);
        int numberOfPosts = postRepository.countAll();

        List<Long> memberIds = posts.stream().map(Post::getMemberId).collect(Collectors.toCollection(ArrayList::new));
        List<Member> members = new ArrayList<>();
        for (Long memberId : memberIds) {
            members.add(this.memberRepository.findById(memberId));
        }
        List<PostSummaryDto> items = posts.stream()
                .map(post -> {
                    Member member = members.stream().filter((m) -> m.getId().equals(post.getMemberId())).findFirst().orElse(null);
                    assert member != null;
                    return new PostSummaryDto(post, member);
                }).collect(Collectors.toList());
        return new Page<>(size, numberOfPosts, items);
    }

    public Long save(Post post, LoginUser loginUser) {
        post.setMemberId(loginUser.getId());
        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    public PostDetailDto findOne(Long id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new PostNotFoundException();
        };
        return new PostDetailDto(post);
    }

    public boolean update(Long id, UpdatePostDto request) {
        Post post = postRepository.findById(id);
        if (post == null) return false;
        post.update(request);

        return true;
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
