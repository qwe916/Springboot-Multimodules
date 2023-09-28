package com.example.api.domain.member.service;

import com.example.core.domain.member.entity.Member;
import com.example.core.domain.member.repository.MemberRepository;
import com.example.api.domain.member.model.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member create(MemberCreateRequest request) {
        Member member = request.toEntity();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
