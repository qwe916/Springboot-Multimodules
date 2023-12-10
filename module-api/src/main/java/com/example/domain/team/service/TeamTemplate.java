package com.example.domain.team.service;

public class TeamTemplate {
    public <T> T execute(TeamCallback<T> teamCallback) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직
        T result = teamCallback.call();

        long endTime = System.currentTimeMillis();
        System.out.println("수행 시간 : " + (endTime - startTime) + "ms");

        return result;
    }
}
