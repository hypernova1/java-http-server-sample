package org.sam.api.member;

import org.sam.server.annotation.component.Repository;
import org.sam.sqlexecutor.DataSource;
import org.sam.sqlexecutor.DefaultSqlExecutor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository extends DefaultSqlExecutor<Member> {

    public MemberRepository(DataSource dataSource) {
        super(dataSource);
    }

    {
        String createTableSQL = "CREATE TABLE If Not Exists member ( " +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "email VARCHAR(255) UNIQUE NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL " +
                ");";

        this.execute(createTableSQL);
    }

    public Member save(Member member) {
        int id = this.insert("insert into member (email, name, password, created_at, updated_at) values(?, ?, ?, now(), now())",
                member.getEmail(), member.getName(), member.getPassword());
        member.setId(Integer.toUnsignedLong(id));

        return member;
    }

    public List<Member> findAll() {
        return this.selectAll("SELECT * FROM member");
    }

    public Member findByEmail(String email) {
        return this.selectOne("SELECT * FROM member WHERE email = ?", email);
    }

    public Member findById(Long id) {
        return this.selectOne("SELECT * FROM member WHERE id = ?", id);
    }
}
