package com.example.domain.member.repository;
import com.example.common.aop.ExecutionTimer;
import com.example.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ExecutionTimer.class})
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장한다.")
    void save() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .age((byte) 20)
                .build();
        //when
        Member savedMember = memberRepository.save(member);
        //then
        assertEquals(member, savedMember);
    }
}