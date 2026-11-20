package com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.resources;

public record SamplingPlanResource(
        Long id,
        String planCode,
        String stationId,
        String samplingWindowStartDate,
        String samplingWindowEndDate,
        Integer sampleVolumeQuantity,
        String sampleVolumeUnit,
        Integer priorityLevel,
        String status,
        String samplingNotes) {
}
