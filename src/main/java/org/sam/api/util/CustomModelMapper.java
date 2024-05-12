package org.sam.api.util;

import org.sam.api.post.Post;
import org.sam.api.post.PostDto;
import org.sam.server.annotation.component.Component;

/**
 * Created by melchor
 * Date: 2020/08/28
 * Time: 9:25 PM
 */
@Component
public class CustomModelMapper {

    public void map(Post post, PostDto.ListResponse listResponse) {
        listResponse.setWriter(post.getMember().getName());
        listResponse.setRegDate(post.getMember().toString());
    }

    public void map(Post post, PostDto.DetailResponse listResponse) {
        listResponse.setWriter(post.getMember().getName());
        listResponse.setRegDate(post.getMember().toString());
    }

}
