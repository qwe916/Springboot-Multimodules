package com.example.api.domain.member.controller;

import com.example.api.domain.team.model.TeamResponse;
import com.example.domain.member.entity.Member;
import com.example.api.domain.member.model.MemberCreateRequest;
import com.example.api.domain.member.service.MemberService;
import com.example.domain.team.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Member> create(@RequestBody MemberCreateRequest request) {
        Member member = memberService.create(request);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> findAll() {
        List<Member> members = memberService.findAll();

        return ResponseEntity.ok(members);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> findById(@PathVariable Long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/members/{id}/teams")
    public ResponseEntity<List<TeamResponse>> findTeamById(@PathVariable Long id) {
        List<TeamResponse> teams = memberService.findAllTeamsById(id);

        return ResponseEntity.ok(teams);
    }

    @PatchMapping("/members/{id}/teams")
    public ResponseEntity<Team> joinTeam(@PathVariable Long id, @RequestParam(value = "teamId") Long teamId) {
        Team createdTeam = memberService.joinTeam(id, teamId);

        return ResponseEntity.ok(createdTeam);
    }
}
