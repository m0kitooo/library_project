package com.app.libraryproject.mapper;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.dto.SendProposalResponse;
import com.app.libraryproject.entity.Proposal;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProposalMapper {

    SendProposalResponse toResponse(Proposal proposal);
}
