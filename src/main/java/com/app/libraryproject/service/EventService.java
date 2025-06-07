package com.app.libraryproject.service;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.entity.EventPlan;
import jdk.jfr.Event;
import org.springframework.http.ResponseEntity;

public interface EventService {
    SendProposalResponse addProposal(SendProposalRequest request);

    EventPlan acceptProposal(Long proposalId, Long organizerId);
    void rejectProposal(Long proposalId);
    void modifyProposal(ModifyProposalRequest request);
}
