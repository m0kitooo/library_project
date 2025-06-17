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

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers(@RequestParam(name = "cardId", required = false) Long cardId) {
        if (cardId != null) {
            return ResponseEntity.ok(memberService.findByLibraryCardId(cardId));
        }
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping
    public ResponseEntity<MemberResponse> save(@RequestBody CreateMemberRequest request) {
        return new ResponseEntity<>(memberService.register(request), HttpStatus.CREATED);
    }
}