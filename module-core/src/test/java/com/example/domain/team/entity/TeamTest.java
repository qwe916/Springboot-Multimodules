package com.example.domain.team.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

class TeamTest {
    Team team;

    @BeforeEach
    void setUp() {
        team = Team.builder()
                .name("팀1")
                .build();
    }

    @Test
    void 리플렉션_테스트_클래스_이름_조회() {
        // 리플렉션을 통해 클래스의 이름을 조회할 수 있다.
        System.out.println(team.getClass().getName());
    }

    @Test
    void 리플레션_테스트_클래스_이름으로_해당_클래스_조회() throws ClassNotFoundException {
        // 리플렉션을 통해 클래스의 이름으로 해당 클래스를 조회할 수 있다.
        Class<?> aClass = Class.forName("com.example.domain.team.entity.Team");
        System.out.println(aClass.getName());
    }

    @Test
    void 리플렉션_테스트_모든_생성자_조회() {
        // 리플렉션을 통해 모든 생성자를 조회할 수 있다.
        Arrays.stream(team.getClass()
                        .getDeclaredConstructors())
                .forEach(System.out::println);
    }

    @Test
    void 리플렉션_테스트_PUBLIC_생성자_조회() {
        // 리플렉션을 통해 public 생성자를 조회할 수 있다.
        Arrays.stream(team.getClass()
                        .getConstructors())
                .forEach(System.out::println);
    }

    @Test
    void 리플렉션_테스트_PRIVATE_메서드_조회() throws NoSuchMethodException {
        // 리플렉션을 통해 private 메서드를 조회할 수 있다.
        Method method = team.getClass().getDeclaredMethod("getTeamName");
        System.out.println(method);
    }

    @Test
    void 리플렉션_테스트_모든_메서드_조회() {
        // 리플렉션을 통해 모든 메서드를 조회할 수 있다. (상속받은 메서드 포함, 접근 제한자 상관 없음)
        Arrays.stream(team.getClass().getDeclaredMethods())
                .forEach(System.out::println);
    }

    @Test
    void 리플렉션_테스트_모든_필드_조회() {
        // 리플렉션을 통해 모든 필드를 조회할 수 있다. (상속받은 필드 포함, 접근 제한자 상관 없음)
        Arrays.stream(team.getClass().getDeclaredFields())
                .forEach(System.out::println);
    }

    @Test
    void 리플렉션_테스트_필드_변경() throws NoSuchFieldException {
        // 리플렉션을 통해 private 필드를 변경할 수 있다.
        Field field = team.getClass().getDeclaredField("name");
        field.setAccessible(true); // private 필드에 접근하기 위해선 필요하다.
        try {
            field.set(team, "팀2");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(team.getName());
    }
}