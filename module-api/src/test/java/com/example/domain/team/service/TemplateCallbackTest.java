package com.example.domain.team.service;

import com.example.domain.team.entity.Team;
import com.example.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TemplateCallbackTest {
    @MockBean
    private TeamRepository teamRepository;

    @Test
    void 템플릿_콜백_패턴() {
        TeamTemplate teamTemplate = new TeamTemplate();
        // 익명 클래스
        teamTemplate.execute(new TeamCallback() {
            @Override
            public List<Team> call() {
                return teamRepository.findAll();
            }
        });
        // 람다식
        teamTemplate.execute(() -> teamRepository.findAll());
    }
}