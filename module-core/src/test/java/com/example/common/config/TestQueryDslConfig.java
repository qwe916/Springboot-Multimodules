package com.codingbottle.common.config;

import com.codingbottle.domain.quiz.repo.QuizQueryRepository;
import com.codingbottle.domain.quiz.repo.UserQuizQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@TestConfiguration
@EnableJpaAuditing
public class TestQueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public QuizQueryRepository quizRepository() {
        return new QuizQueryRepository(jpaQueryFactory());
    }

    @Bean
    public UserQuizQueryRepository userQuizRepository() {
        return new UserQuizQueryRepository(jpaQueryFactory());
    }
}