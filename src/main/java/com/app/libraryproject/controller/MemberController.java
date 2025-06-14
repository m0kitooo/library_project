package com.app.libraryproject.controller;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.dto.user.GetPersonListRequest;
import com.app.libraryproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable Long id) {
        return memberService.find(id);
    }

    @GetMapping
    public List<MemberResponse> getMembers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "") String filterFullName
    ) { return memberService.findAll(page, limit, filterFullName); }

    @PostMapping
    public ResponseEntity<MemberResponse> save(@RequestBody CreateMemberRequest request) {
        return new ResponseEntity<>(memberService.register(request), HttpStatus.CREATED);
    }
}
