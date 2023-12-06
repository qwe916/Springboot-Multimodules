package com.example.domain.member.service;

import com.example.api.domain.enrollment.service.EnrollmentService;
import com.example.api.domain.member.model.MemberCreateRequest;
import com.example.api.domain.team.model.TeamResponse;
import com.example.api.domain.team.service.TeamService;
import com.example.domain.enrollment.entity.Enrollment;
import com.example.domain.member.entity.Member;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.team.entity.Team;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final EnrollmentService enrollmentService;
    private final TeamService teamService;
    private final EntityManager entityManager;

    @Transactional
    public Member create(MemberCreateRequest request) {
        Member member = request.toEntity();

        Member saveMember = memberRepository.save(member);

        if (saveMember.getAge() == 20) {
            throw new RuntimeException("나이가 20살 이상인 회원만 등록할 수 있습니다.");
        }

        saveMember.updateAge((byte) 21);

        return saveMember;
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

    @Transactional
    public void update(Member member, int age, String name) {
        updateName(member, name);
        if (age == 20) {
            throw new RuntimeException("나이가 20살 이상인 회원만 등록할 수 있습니다.");
        }
        member.updateAge((byte) age);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateName(Member member, String name) {
        member.updateName(name);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Member getAndUpdate(Long id) {
        Session session = entityManager.unwrap(Session.class);
        session.setHibernateFlushMode(org.hibernate.FlushMode.MANUAL);
        Member member = memberRepository.findById(id).orElseThrow();
        member.updateName("홍길동2");
        return member;
    }
}
