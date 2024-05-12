package org.sam.api.auth;

import org.sam.api.member.Member;
import org.sam.api.member.MemberRepository;
import org.sam.api.util.ModelMapper;
import org.sam.server.annotation.component.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final MemberRepository members;
    private final ModelMapper modelMapper;

    public AuthService(MemberRepository members, ModelMapper modelMapper) {
        this.members = members;
        this.modelMapper = modelMapper;
    }

    public Member login(LoginRequest request) {
        Member member = members.findByEmail(request.getEmail()).orElse(null);
        if (member == null) return null;
        boolean result = request.getPassword().equals(member.getPassword());
        if (!result) return null;
        return member;
    }

    public boolean join(JoinRequest request) {
        boolean availableEmail = isAvailableEmail(request.getEmail());
        if (!availableEmail) return false;
        Member member = modelMapper.convert(request, Member.class);
        member.setCreatedAt(LocalDateTime.now());
        members.save(member);
        return true;
    }

    public boolean isAvailableEmail(String email) {
        Member member = members.findByEmail(email).orElse(null);
        return member == null;
    }
}
