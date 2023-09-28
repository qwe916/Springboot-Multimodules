package com.example.core.domain.team.repository;

import com.example.core.domain.member.entity.Member;
import com.example.core.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

