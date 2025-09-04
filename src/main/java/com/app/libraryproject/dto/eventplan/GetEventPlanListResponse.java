package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.entity.EventPlan;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
@Builder
public class GetEventPlanListResponse {
    List<EventPlanDto> plans;
    int page;
    int size;
    int totalPages;
    long totalElements;

    public static GetEventPlanListResponse fromPage(Page<EventPlan> page) {
        List<EventPlanDto> items = page.getContent()
                .stream()
                .map(EventPlanDto::fromEntity)
                .toList();

        return GetEventPlanListResponse.builder()
                .plans(items)
                .page(page.getNumber())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    public static GetEventPlanListResponse map(Page<EventPlan> page) {
        return fromPage(page);
    }
}
