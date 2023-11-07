package com.example.domain.enrollment.entity;

import com.example.common.entity.BaseEntity;
import com.example.domain.member.entity.Member;
import com.example.domain.team.entity.Team;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Enrollment(Team team, Member member) {
        this.team = team;
        this.member = member;
    }
}
