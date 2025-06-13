package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.GetProposalDetailsResponse;
import com.app.libraryproject.dto.user.*;
import com.app.libraryproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    private final UserServiceImpl userService;

    @PostMapping("/details")
    public ResponseEntity<GetProposalDetailsResponse> getProposalDetails(@RequestParam Long memberId) {
        return ResponseEntity.ok(
                userService.getPersonDetails(memberId)
        );
    }

    @PostMapping("/list")
    public ResponseEntity<GetPersonListResponse> getMemberList(@RequestBody GetPersonListRequest request) {
        return ResponseEntity.ok(
                userService.getUserList(request)
        );
    }
}