package com.example.domain.member.repository;

import com.example.common.aop.ExecutionTimer;
import com.example.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    @DisplayName("회원을 정보를 업데이트한다.")
    void findById() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .age((byte) 20)
                .build();
        Member savedMember = memberRepository.save(member);
        //when
        savedMember.updateName("홍길동2");
        memberRepository.flush();
        //then
        assertThat(savedMember.getName()).isEqualTo("홍길동2");
    }

    @Test
    @DisplayName("회원 정보를 업데이트를 하는데 새로운 객체를 만들어서 업데이트를 한다.")
    void update() {
        //given
        Member member = Member.builder()
                .name("홍길동")
                .age((byte) 20)
                .build();
        Member savedMember = memberRepository.save(member);
        //when
        Member findMember = memberRepository.findById(savedMember.getId()).orElseThrow();

        Member newMember = Member.builder()
                .id(findMember.getId())
                .name("홍길동2")
                .age((byte) 20)
                .build();

        Member updateMember = memberRepository.save(newMember);
        memberRepository.delete(findMember);
        //then
        assertEquals(updateMember.getName(), "홍길동2");
    }
}