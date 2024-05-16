package org.sam.api.auth;

import org.sam.api.member.Member;
import org.sam.api.member.MemberH2Repository;
import org.sam.api.member.MemberRepository;
import org.sam.api.util.ModelMapper;
import org.sam.server.annotation.component.Service;

import java.util.Date;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public AuthService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    public Member login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail());
        if (member == null) return null;
        boolean result = request.getPassword().equals(member.getPassword());
        if (!result) return null;
        return member;
    }

    public boolean join(JoinRequest request) {
        boolean existsEmail = existsEmail(request.getEmail());
        if (existsEmail) return false;
        Member member = modelMapper.convert(request, Member.class);
        member.setCreatedAt(new Date());
        memberRepository.save(member);
        return true;
    }

    public boolean existsEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member != null;
    }
}
