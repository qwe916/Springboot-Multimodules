package com.example.domain.team.entity;

import com.example.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Team(String name) {
        this.name = name;
    }

    private String getTeamName() {
        return this.name;
    }

    public void printTeamName() {
        System.out.println(this.getTeamName());
    }
}
