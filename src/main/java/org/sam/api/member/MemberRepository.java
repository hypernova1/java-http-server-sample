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

    private Long id = 1L;
    List<Member> memberList = new ArrayList<>();

    public MemberRepository(DataSource dataSource) {
        super(dataSource);
    }

    {
        Member admin = new Member();
        admin.setId(this.id++);
        admin.setEmail("admin@co.kr");
        admin.setPassword("1111");
        admin.setName("admin");
        admin.setCreatedAt(LocalDateTime.now());
        this.insertMember(admin);
    }

    public void insertMember(Member member) {
        this.insert("insert into member (email, name, password, created_at, updated_at) values(?, ?, ?, now(), now())",
                member.getEmail(), member.getName(), member.getPassword());
    }

    public Optional<Member> findByEmail(String email) {
        return memberList.stream()
                .filter(member -> member.getEmail().equals(email)).findFirst();
    }

    public Member save(Member member) {
        member.setId(this.id++);
        memberList.add(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return memberList.stream().filter(member -> member.getId().equals(id)).findFirst();
    }
}
