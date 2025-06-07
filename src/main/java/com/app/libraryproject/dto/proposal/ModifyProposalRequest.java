package com.app.libraryproject.dto.proposal;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ModifyProposalRequest {
    private Long id;
    private String title;
    private String description;
}
