package com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects;

import java.time.LocalDate;

public record SamplingWindow(LocalDate startDate, LocalDate endDate) {
    public SamplingWindow {
        if (startDate == null) {
            throw new IllegalArgumentException("Sampling window start date must not be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("Sampling window end date must not be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Sampling window end date must be after the start date");
        }
    }

    public boolean contains(LocalDate checkDate) {
        if (checkDate == null) {
            throw new IllegalArgumentException("Check date must not be null");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
