package com.example.domain.member.service;

import com.example.api.domain.member.model.MemberCreateRequest;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class TransactionTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void 트랜잭션_전파_REQUIRED_테스트() {
        // propagation = Propagation.REQUIRED 일 경우 트랜잭션을 전파받아서 같은 트랜잭션에서 실행된다.
        assertThrows(RuntimeException.class, () -> {
            memberService.create(MemberCreateRequest.builder()
                    .name("홍길동")
                    .age((byte) 20)
                    .build());
        });

        assertThat(memberRepository.findAll()).hasSize(0);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void 트랜잭션_전파_REQUIRES_NEW_테스트() {
        // propagation = Propagation.REQUIRES_NEW 일 경우 트랜잭션을 새로 만들어서 실행된다.
        Member member = memberService.create(MemberCreateRequest.builder()
                .name("홍길동")
                .age((byte) 21)
                .build());

        assertThrows(RuntimeException.class, () -> {
            memberService.update(member, 20, "홍길동2");
        });

        //updateName은 트랜잭션이 새로 만들어져 실행되어 다른 메서드에서 오류가 발생해도 해당 트랜잭션은 롤백되지 않는다.
        assertAll(
                () -> assertThat(member.getAge()).isEqualTo((byte) 21),
                () -> assertThat(member.getName()).isEqualTo("홍길동2")
        );
    }

    @Test
    void 트랜잭션_전파_MANDATORY_테스트() {
        // propagation = Propagation.MANDATORY 일 경우 트랜잭션이 없으면 예외가 발생한다.
        Member member = memberService.create(MemberCreateRequest.builder()
                .name("홍길동")
                .age((byte) 22)
                .build());

        // 트랜잭션이 없는 상태에서 트랜잭션이 필요한 메서드를 호출하면 예외가 발생한다.
        assertThrows(RuntimeException.class, () -> {
            memberService.delete(member);
        });
    }
}