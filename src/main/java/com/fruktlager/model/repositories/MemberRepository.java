package com.fruktlager.model.repositories;

import com.fruktlager.model.Member;

import java.util.Optional;

public interface MemberRepository {
    Member get(Integer number);

    void save(Member client);

    Optional<Member> getByUsername(String username);

    Optional<Member> getByLoginData(String email, String password);
}
