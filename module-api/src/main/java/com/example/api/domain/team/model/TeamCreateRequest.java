package com.example.api.domain.team.model;

import com.example.core.domain.team.entity.Team;

public record TeamCreateRequest(
        String name
) {
    public Team toEntity() {
        return Team.builder()
                .name(name)
                .build();
    }
}
