package com.example.api.domain.member.controller;

import com.example.core.domain.member.entity.Member;
import com.example.api.domain.member.model.MemberCreateRequest;
import com.example.api.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
