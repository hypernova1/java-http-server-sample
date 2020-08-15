package org.sam.api.repositoty;

import org.sam.api.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private Long id = 1L;
    List<Member> memberList = new ArrayList<>();

    {
        Member admin = new Member();
        admin.setId(this.id++);
        admin.setEmail("admin@co.kr");
        admin.setPassword("1111");
        admin.setName("admin");
        memberList.add(admin);
    }

    public Member findByEmail(String email) {
        return memberList.stream()
                .filter(member -> member.getEmail().equals(email)).findFirst().get();
    }

    public void save(Member member) {
        member.setId(this.id++);
        memberList.add(member);
    }
}
