package com.example.api.domain.team.model;

import com.example.core.domain.team.entity.Team;
import lombok.Builder;

@Builder
public record TeamResponse(
        Long id,
        String name
) {
    public static TeamResponse of(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
    }
}
