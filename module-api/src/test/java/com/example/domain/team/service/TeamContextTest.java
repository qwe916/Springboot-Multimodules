package com.example.domain.team.service;

import com.example.domain.team.entity.Team;
import com.example.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TeamContextTest {
    @MockBean
    private TeamRepository teamRepository;

    @Test
    void 클래스를_이용한_전략_패턴() {
        TeamStrategyLogic teamStrategyLogic = new TeamStrategyLogic(teamRepository);
        TeamContext teamContext = new TeamContext(teamStrategyLogic);
        List<Team> teams = teamContext.execute();
    }

    @Test
    void 익명_클래스를_이용한_전략_패턴() {
        TeamContext teamContext = new TeamContext(new TeamStrategy<List<Team>>() {
            @Override
            public List<Team> call() {
                return teamRepository.findAll();
            }
        });
        List<Team> teams = teamContext.execute();
    }

    @Test
    void 람다_식을_이용한_전략_패턴() {
        TeamContext teamContext = new TeamContext(() -> teamRepository.findAll());
        List<Team> teams = teamContext.execute();
    }

    @Test
    void 전략을_파라미터로_받아_사용하는_전략_패턴() {
        TeamContext teamContext = new TeamContext();
        List<Team> teams = teamContext.executeWithParameterStrategy(new TeamStrategy<List<Team>>() {
            @Override
            public List<Team> call() {
                return teamRepository.findAll();
            }
        });

        List<Team> teams2 = teamContext.executeWithParameterStrategy(() -> teamRepository.findAll());
    }
}