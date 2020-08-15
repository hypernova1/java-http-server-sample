package org.sam.api.service;

import org.sam.api.domain.Member;
import org.sam.api.payload.JoinReqeust;
import org.sam.api.payload.LoginRequest;
import org.sam.api.repositoty.MemberRepository;
import org.sam.server.annotation.component.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail());
        if (member == null) return false;
        return request.getPassword().equals(member.getPassword());
    }

    public void join(JoinReqeust request) {
        Member member = Member.create(request);
        memberRepository.save(member);
    }
}
