package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.*;

public interface ProposalService {
    Long addProposal(SendProposalRequest request);

    Long acceptProposal(Long proposalId, Long organizerId);
    void rejectProposal(Long proposalId);
    void modifyProposal(ModifyProposalRequest request);
    GetProposalDetailsResponse getProposalDetails(Long proposalId);
    GetProposalListResponse getProposalList(GetProposalListRequest request);
}
