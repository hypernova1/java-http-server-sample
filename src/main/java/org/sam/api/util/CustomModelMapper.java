package org.sam.api.util;

import org.sam.api.post.application.payload.out.PostDetailDto;
import org.sam.api.post.application.payload.out.PostSummaryDto;
import org.sam.api.post.domain.Post;
import org.sam.server.annotation.component.Component;

/**
 * Created by melchor
 * Date: 2020/08/28
 * Time: 9:25 PM
 */
@Component
public class CustomModelMapper {

    public void map(Post post, PostSummaryDto postSummaryDto) {
        postSummaryDto.setWriter(post.getMember().getName());
        postSummaryDto.setCreatedAt(post.getMember().toString());
    }

    public void map(Post post, PostDetailDto postDetailDto) {
        postDetailDto.setWriter(post.getMember().getName());
        postDetailDto.setCreatedAt(post.getMember().toString());
    }

}
