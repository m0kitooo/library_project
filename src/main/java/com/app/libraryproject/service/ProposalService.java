package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.model.ProposalStatus;

public interface ProposalService {
    Long addProposal(SendProposalRequest request);

    Long acceptProposal(Long proposalId, Long organizerId);
    void rejectProposal(Long proposalId);
    void modifyProposal(ModifyProposalRequest request);
    ProposalResponse find(Long proposalId);
    GetProposalListResponse getProposals(
            ProposalStatus status,
            Integer page,
            Integer limit
    );
}