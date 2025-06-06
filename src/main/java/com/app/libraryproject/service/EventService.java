package com.app.libraryproject.service;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.dto.SendProposalResponse;
import org.springframework.http.ResponseEntity;

public interface EventService {
    ResponseEntity<SendProposalResponse> addProposal(SendProposalRequest request);
}
