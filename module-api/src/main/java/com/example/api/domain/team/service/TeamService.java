package com.example.api.domain.team.service;

import com.example.api.domain.enrollment.service.EnrollmentService;
import com.example.api.domain.member.model.MemberResponse;
import com.example.api.domain.team.model.TeamCreateRequest;
import com.example.api.domain.team.model.TeamResponseWithMember;
import com.example.core.domain.enrollment.entity.Enrollment;
import com.example.core.domain.team.entity.Team;
import com.example.core.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;
    private final EnrollmentService enrollmentService;

    @Transactional
    public Team create(TeamCreateRequest request) {
        Team team = request.toEntity();

        return teamRepository.save(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }


    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
    }

    public TeamResponseWithMember findByIdWithMembers(Long id) {
        Team team = findById(id);

        List<MemberResponse> members = enrollmentService.findAllByTeam(team).stream()
                .map(Enrollment::getMember)
                .map(MemberResponse::of)
                .toList();

        return TeamResponseWithMember.of(team, members);
    }
}
