package com.example.api.domain.team.model;

import com.example.api.domain.member.model.MemberResponse;
import com.example.core.domain.team.entity.Team;
import lombok.Builder;

import java.util.List;

@Builder
public record TeamResponseWithMember(
        Team team,
        List<MemberResponse> members
) {
    public static TeamResponseWithMember of(Team team, List<MemberResponse> members) {
        return TeamResponseWithMember.builder()
                .team(team)
                .members(members)
                .build();
    }
}
