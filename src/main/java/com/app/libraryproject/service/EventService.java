package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.DecideProposalRequest;
import com.app.libraryproject.dto.proposal.DecideProposalResponse;
import com.app.libraryproject.dto.proposal.SendProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalResponse;

public interface EventService {
    SendProposalResponse addProposal(SendProposalRequest request);
    DecideProposalResponse decideProposal(DecideProposalRequest request);
}
