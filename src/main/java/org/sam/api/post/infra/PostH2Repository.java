package org.sam.api.post.infra;

import org.sam.api.post.domain.Post;
import org.sam.api.post.domain.PostRepository;
import org.sam.server.annotation.component.Repository;
import org.sam.sqlexecutor.DataSource;
import org.sam.sqlexecutor.DefaultSqlExecutor;

import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:33 PM
 */
@Repository
public class PostH2Repository extends DefaultSqlExecutor<Post> implements PostRepository {

    public PostH2Repository(DataSource dataSource) {
        super(dataSource);
    }

    {
        String createTableSQL = "CREATE TABLE If Not Exists post ( " +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(255) NOT NULL, " +
                "content TEXT NOT NULL, " +
                "member_id BIGINT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL " +
                ");";

        this.execute(createTableSQL);
    }

    @Override
    public List<Post> findAll(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM post ORDER BY id DESC LIMIT ? OFFSET ?";
        return this.selectAll(sql, size, offset);
    }

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO post (title, content, member_id, created_at, updated_at) VALUES (?, ?, ?, now(), now())";
        int id = this.insert(sql, post.getTitle(), post.getContent(), post.getMemberId());
        post.setId(Integer.toUnsignedLong(id));
        return post;
    }

    @Override
    public Post findById(Long id) {
        return this.selectOne("SELECT * FROM post WHERE id = ?", id);
    }

    @Override
    public void delete(Long id) {
        this.execute("DELETE FROM post WHERE id = " + id);
    }

    @Override
    public int countAll() {
        return this.count("SELECT COUNT(*) FROM post");
    }
}
