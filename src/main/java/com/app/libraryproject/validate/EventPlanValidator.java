package com.app.libraryproject.validate;

import com.app.libraryproject.entity.EventPlan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EventPlanValidator {
    public static List<String> validateForFinalApproval(EventPlan ep) {
        List<String> errors = new ArrayList<>();

        if (ep.getId() == null)
            errors.add("Plan ID is required");

        if (ep.getName() == null || ep.getName().isBlank())
            errors.add("Plan name is required");

        if (ep.getDescription() == null || ep.getDescription().isBlank())
            errors.add("Plan description is required");

        if (ep.getStatus() == null)
            errors.add("Plan status is required");

        if (ep.getStartTime() == null)
            errors.add("Start time is required");

        if (ep.getEndTime() == null)
            errors.add("End time is required");

        if (ep.getStartTime() != null && ep.getEndTime() != null &&
                !ep.getEndTime().isAfter(ep.getStartTime())) {
            errors.add("End time must be after start time");
        }

        if (ep.getEstimatedPrice() == null || ep.getEstimatedPrice().compareTo(BigDecimal.ZERO) > 0) {
            errors.add("Estimated price is required and must be greater than zero");
        }

        return errors;
    }
}
