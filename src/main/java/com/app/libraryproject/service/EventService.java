package com.app.libraryproject.service;

import com.app.libraryproject.dto.*;
import org.springframework.http.ResponseEntity;

public interface EventService {
    SendProposalResponse addProposal(SendProposalRequest request);
    DecideProposalResponse decideProposal(DecideProposalRequest request);
}
