package com.example.api.domain.member.model;

import com.example.core.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberCreateRequest(
        String name,
        byte age
) {
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
