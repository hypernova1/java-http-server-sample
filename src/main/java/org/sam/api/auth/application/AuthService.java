package org.sam.api.auth.application;

import org.sam.api.auth.application.payload.JoinRequest;
import org.sam.api.auth.application.payload.LoginRequest;
import org.sam.api.common.exception.BadRequestException;
import org.sam.api.common.exception.UnauthorizedException;
import org.sam.api.member.domain.Member;
import org.sam.api.member.domain.MemberRepository;
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
        if (member == null) {
            throw new UnauthorizedException();
        };

        boolean result = request.getPassword().equals(member.getPassword());
        if (!result) {
            throw new UnauthorizedException();
        };

        return member;
    }

    public void join(JoinRequest request) {
        boolean existsEmail = existsEmail(request.getEmail());
        if (existsEmail) {
            throw new BadRequestException();
        }

        Member member = modelMapper.convert(request, Member.class);
        member.setCreatedAt(new Date());
        memberRepository.save(member);
    }

    public boolean existsEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member != null;
    }
}
