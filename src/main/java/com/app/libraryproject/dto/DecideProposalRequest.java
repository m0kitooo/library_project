package com.app.libraryproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DecideProposalRequest {
    private Long id;
    private boolean accepted;
    private String title;
    private String description; //title i description potrzebny po to, aby można byłoby zaakptować propozycję z poprawką. Puste pole title i/lub description oznaczają, że brak poprawki.
    private Long organizerId;
}
