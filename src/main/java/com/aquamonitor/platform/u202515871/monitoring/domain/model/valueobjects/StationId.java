package com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects;

import java.util.UUID;

public record StationId(UUID value) {
    public StationId {
        if (value == null) {
            throw new IllegalArgumentException("Station id must not be null");
        }
    }
}
