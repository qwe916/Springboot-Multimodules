package com.example.api.domain.member.service;

import com.example.api.domain.enrollment.service.EnrollmentService;
import com.example.api.domain.team.model.TeamResponse;
import com.example.api.domain.team.service.TeamService;
import com.example.domain.enrollment.entity.Enrollment;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.api.domain.member.model.MemberCreateRequest;
import com.example.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final EnrollmentService enrollmentService;
    private final TeamService teamService;

    @Transactional
    public Member create(MemberCreateRequest request) {
        Member member = request.toEntity();

        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    public List<TeamResponse> findAllTeamsById(Long id) {
        Member member = findById(id);

        return enrollmentService.findAllByMember(member).stream()
                .map(Enrollment::getTeam)
                .map(TeamResponse::of)
                .toList();
    }

    @Transactional
    public Team joinTeam(Long id, Long teamId) {
        Member member = findById(id);

        Team team = teamService.findById(teamId);

        enrollmentService.enroll(member, team);

        return team;
    }
}
