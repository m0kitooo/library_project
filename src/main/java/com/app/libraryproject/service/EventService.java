package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.ModifyProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalRequest;
import com.app.libraryproject.entity.EventPlan;

public interface EventService {
    Long addProposal(SendProposalRequest request);

    EventPlan acceptProposal(Long proposalId, Long organizerId);
    void rejectProposal(Long proposalId);
    void modifyProposal(ModifyProposalRequest request);
    //GetProposalDetailsResponse getProposalDetails(Long proposalId);
}