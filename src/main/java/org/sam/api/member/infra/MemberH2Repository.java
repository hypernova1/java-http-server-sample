package org.sam.api.member.infra;

import org.sam.api.member.domain.Member;
import org.sam.api.member.domain.MemberRepository;
import org.sam.server.annotation.component.Repository;
import org.sam.sqlexecutor.DataSource;
import org.sam.sqlexecutor.DefaultSqlExecutor;

import java.util.List;

@Repository
public class MemberH2Repository extends DefaultSqlExecutor<Member> implements MemberRepository {

    public MemberH2Repository(DataSource dataSource) {
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

    @Override
    public Member save(Member member) {
        int id = this.insert("insert into member (email, name, password, created_at, updated_at) values(?, ?, ?, now(), now())",
                member.getEmail(), member.getName(), member.getPassword());
        member.setId(Integer.toUnsignedLong(id));

        return member;
    }

    @Override
    public List<Member> findAll() {
        return this.selectAll("SELECT * FROM member");
    }

    @Override
    public Member findByEmail(String email) {
        return this.selectOne("SELECT * FROM member WHERE email = ?", email);
    }

    public Member findById(Long id) {
        return this.selectOne("SELECT * FROM member WHERE id = ?", id);
    }
}
