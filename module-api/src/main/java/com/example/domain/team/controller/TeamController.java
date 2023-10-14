package com.example.api.domain.team.controller;

import com.example.api.domain.team.model.TeamCreateRequest;
import com.example.api.domain.team.model.TeamResponseWithMember;
import com.example.api.domain.team.service.TeamService;
import com.example.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/teams")
    public ResponseEntity<Team> create(@RequestBody TeamCreateRequest request) {
        Team team = teamService.create(request);

        return ResponseEntity.ok(team);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = teamService.findAll();

        return ResponseEntity.ok(teams);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamResponseWithMember> findByIdWithMembers(@PathVariable Long id) {
        TeamResponseWithMember teamResponseWithMember = teamService.findByIdWithMembers(id);

        return ResponseEntity.ok(teamResponseWithMember);
    }
}
