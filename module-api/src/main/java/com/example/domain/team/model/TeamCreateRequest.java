package com.example.api.domain.team.model;

import com.example.domain.team.entity.Team;

public record TeamCreateRequest(
        String name
) {
    public Team toEntity() {
        return Team.builder()
                .name(name)
                .build();
    }
}
