package org.sam.api.service;

import org.sam.api.domain.Member;
import org.sam.api.payload.JoinRequest;
import org.sam.api.payload.LoginRequest;
import org.sam.api.repositoty.MemberRepository;
import org.sam.server.annotation.component.Service;

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

    public boolean join(JoinRequest request) {
        boolean availableEmail = isAvailableEmail(request.getEmail());
        if (!availableEmail) return false;
        Member member = Member.create(request);
        memberRepository.save(member);
        return true;
    }

    public boolean isAvailableEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member == null;
    }
}
