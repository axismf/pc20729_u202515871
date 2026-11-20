package com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects;

public record SampleVolume(Integer quantity, String unit) {
    public SampleVolume {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Sample volume quantity must be greater than zero");
        }
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Sample volume unit must not be null or blank");
        }
    }

    public SampleVolume addVolume(Integer additionalVolume) {
        if (additionalVolume == null || additionalVolume <= 0) {
            throw new IllegalArgumentException("Additional volume must be greater than zero");
        }
        if (additionalVolume > Integer.MAX_VALUE - quantity) {
            throw new IllegalArgumentException("Sample volume quantity must not exceed " + Integer.MAX_VALUE);
        }
        return new SampleVolume(quantity + additionalVolume, unit);
    }
}
