package com.example.domain.team.service;

import com.example.domain.team.entity.Team;

import java.util.List;

public class TeamContext {
    private TeamStrategy<List<Team>> teamStrategy;

    public TeamContext(TeamStrategy<List<Team>> teamStrategy) {
        this.teamStrategy = teamStrategy;
    }

    public TeamContext() {
    }

    public List<Team> execute() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직
        List<Team> teams = teamStrategy.call();

        long endTime = System.currentTimeMillis();
        System.out.println("수행 시간 : " + (endTime - startTime) + "ms");

        return teams;
    }

    public List<Team> executeWithParameterStrategy(TeamStrategy<List<Team>> teamStrategy) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직
        List<Team> teams = teamStrategy.call();

        long endTime = System.currentTimeMillis();
        System.out.println("수행 시간 : " + (endTime - startTime) + "ms");

        return teams;
    }
}
