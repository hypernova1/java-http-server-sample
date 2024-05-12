package org.sam.api.member;

import org.sam.server.annotation.component.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void test() {
        System.out.println("hello");
        memberRepository.insertMember(new Member());
    }

    public Member getMemberInfo(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
