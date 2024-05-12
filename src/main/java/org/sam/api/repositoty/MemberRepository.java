package org.sam.api.repositoty;

import org.sam.api.domain.Member;
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
        admin.setRegDate(LocalDateTime.now());
        memberList.add(admin);
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
