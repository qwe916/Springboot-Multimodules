package com.example.domain.enrollment.repository;

import com.example.domain.enrollment.entity.Enrollment;
import com.example.domain.member.entity.Member;
import com.example.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByMember(Member member);

    List<Enrollment> findAllByTeam(Team team);
}
