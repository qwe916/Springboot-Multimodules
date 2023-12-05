package com.example.domain.member.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {
    @Test
    void builder_pattern_test() {
        Member member = Member.builder()
                .name("홍길동")
                .age((byte) 20)
                .build();
        member.updateName("홍길동2");

        assertEquals(member.getName(), "홍길동2");
    }

}