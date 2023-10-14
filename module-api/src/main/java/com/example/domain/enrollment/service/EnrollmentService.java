package com.example.api.domain.enrollment.service;

import com.example.domain.enrollment.entity.Enrollment;
import com.example.domain.enrollment.repository.EnrollmentRepository;
import com.example.domain.member.entity.Member;
import com.example.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;



    public void enroll(Member member, Team team) {
        Enrollment enrollment = Enrollment.builder()
                .member(member)
                .team(team)
                .build();

        enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findAllByTeam(Team team) {
        return enrollmentRepository.findAllByTeam(team);
    }

    public List<Enrollment> findAllByMember(Member member) {
        return enrollmentRepository.findAllByMember(member);
    }
}
