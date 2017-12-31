package com.fruktlager.ui;

import com.fruktlager.model.Member;
import com.fruktlager.model.repositories.MemberRepository;

import java.util.Optional;

public class AuthenticationManager {

    private MemberRepository memberRepository;
    private Member member;

    public AuthenticationManager(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemeber() {
        return member;
    }

    public boolean authenticate(String username) {
        Optional<Member> memberOptional = memberRepository.getByUsername(username);
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
            return true;
        }
        return false;
    }

    public Integer getMemberNumber() {
        return member.getNumber();
    }
}
