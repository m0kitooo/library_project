package com.app.libraryproject.controller;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.findAll();
    }


}
