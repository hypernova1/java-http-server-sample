package org.sam.api.member;

import org.sam.server.annotation.component.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id);
    }
}
