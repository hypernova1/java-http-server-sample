package org.sam.api.handler;

import org.sam.api.domain.Member;
import org.sam.api.repositoty.MemberRepository;
import org.sam.server.annotation.component.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberInfo(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
