package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.GetProposalDetailsResponse;
import com.app.libraryproject.dto.proposal.ModifyProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalRequest;

public interface EventService {
    Long addProposal(SendProposalRequest request);

    Long acceptProposal(Long proposalId, Long organizerId);
    void rejectProposal(Long proposalId);
    void modifyProposal(ModifyProposalRequest request);
    GetProposalDetailsResponse getProposalDetails(Long proposalId);
}