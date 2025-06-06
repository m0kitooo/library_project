package com.app.libraryproject.service;

import com.app.libraryproject.dto.*;
import org.springframework.http.ResponseEntity;

public interface EventService {
    ResponseEntity<SendProposalResponse> addProposal(SendProposalRequest request);
    ResponseEntity<DecideProposalResponse> decideProposal(DecideProposalRequest request);
}
