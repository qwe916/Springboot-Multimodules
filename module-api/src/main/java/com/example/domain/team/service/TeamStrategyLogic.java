package com.example.domain.team.service;

import com.example.domain.team.entity.Team;
import com.example.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamStrategyLogic implements TeamStrategy<List<Team>> {
    private final TeamRepository teamRepository;

    @Override
    public List<Team> call() {
        //비즈니스 로직 실행
        return teamRepository.findAll();
    }
}
