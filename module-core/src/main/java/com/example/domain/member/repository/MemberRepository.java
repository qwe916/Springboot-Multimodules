package com.example.domain.member.repository;

import com.example.common.annotation.ExeTimer;
import com.example.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
