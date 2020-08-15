package org.sam.api.repositoty;

import org.sam.api.domain.Post;
import org.sam.server.annotation.component.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by melchor
 * Date: 2020/08/11
 * Time: 8:33 PM
 */
@Component
public class BoardDao {

//    private final String url = "jdbc:mysql://localhost:3306/board";
//    private final String id = "root";
//    private final String pw = "1111";
//
//    public Connection getConnection() {
//        try {
//            return DriverManager.getConnection(url, id, pw);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<Post> selectBoardList() {
//        List<Post> postList = new ArrayList<>();
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement("SELECT * FROM post")) {
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                Post post = new Post();
//                post.setId(resultSet.getLong(0));
//                post.setTitle(resultSet.getString(1));
//                post.setContent(resultSet.getString(2));
//                postList.add(post);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return postList;
//    }
//
//    public void insertPost(Post post) {
//        try (Connection connection = getConnection();
//             PreparedStatement ps = connection.prepareStatement("INSERT INTO POST(title, content) VALUES(?, ?)")) {
//            ps.setString(0, post.getTitle());
//            ps.setString(0, post.getContent());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Post> getPostList() {
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Post post = new Post();
            post.setId(i + 1L);
            post.setTitle("Sample Post" + i);
            post.setContent("Sample 입니다.");
            postList.add(post);
        }
        return postList;
    }

}
