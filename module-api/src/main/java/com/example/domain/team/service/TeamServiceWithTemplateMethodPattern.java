package com.example.domain.team.service;

import com.example.domain.team.entity.Team;
import com.example.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceWithTemplateMethodPattern {
    private final TeamRepository teamRepository;

    public Team save(Team team) {
        AbstractTemplate<Team> template = new AbstractTemplate<>() {
            @Override
            public Team call() {
                return teamRepository.save(team);
            }
        };

        return template.execute();
    }
}
