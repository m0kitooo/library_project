package com.app.libraryproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SendProposalResponse {
    private Long id;
    private String title;
    private String description;
    private String proposedBy;
}