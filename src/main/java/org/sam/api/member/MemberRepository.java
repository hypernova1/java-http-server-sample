package org.sam.api.member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findAll();

    Member findByEmail(String email);

    Member findById(Long id);

}
