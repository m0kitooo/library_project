package com.app.libraryproject.controller;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/search")
    public List<MemberResponse> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String pesel
    ) {
        return memberService.searchMembers(name, surname, pesel);
    }

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping(params = "phrase")
    public List<MemberResponse> getMembersByPhrase(@RequestParam String phrase) {
        return memberService.findByPhrase(phrase);
    }

    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.findAll();
    }

    @PostMapping
    public ResponseEntity<MemberResponse> save(@RequestBody CreateMemberRequest request) {
        return new ResponseEntity<>(memberService.register(request), HttpStatus.CREATED);
    }
}
