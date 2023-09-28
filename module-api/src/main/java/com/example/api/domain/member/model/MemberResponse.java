package com.example.api.domain.member.model;

import com.example.core.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long id,
        String name,
        Byte age
) {
    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .build();
    }
}
