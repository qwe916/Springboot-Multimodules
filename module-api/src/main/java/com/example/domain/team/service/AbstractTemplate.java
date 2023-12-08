package com.example.domain.team.service;

public abstract class AbstractTemplate<T> {
    public abstract T call();

    public T execute() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직
        T result = this.call();

        long endTime = System.currentTimeMillis();
        System.out.println("수행 시간 : " + (endTime - startTime) + "ms");

        return result;
    }
}
