package com.example.domain.member.repository;

import com.example.domain.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final QMember qMember = QMember.member;

    public boolean existsByAge(byte age) {
        return jpaQueryFactory.selectOne()
                .from(qMember)
                .where(qMember.age.eq(age))
                .fetchFirst() != null;
    }
}
