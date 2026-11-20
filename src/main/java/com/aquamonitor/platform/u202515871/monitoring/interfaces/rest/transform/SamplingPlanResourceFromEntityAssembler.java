package com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.transform;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.resources.SamplingPlanResource;

public final class SamplingPlanResourceFromEntityAssembler {
    private SamplingPlanResourceFromEntityAssembler() {
    }

    public static SamplingPlanResource toResourceFromEntity(SamplingPlan entity) {
        return new SamplingPlanResource(
                entity.getId(),
                entity.getPlanCode().code(),
                entity.getStationId().value().toString(),
                entity.getSamplingWindow().startDate().toString(),
                entity.getSamplingWindow().endDate().toString(),
                entity.getSampleVolume().quantity(),
                entity.getSampleVolume().unit(),
                entity.getPriorityLevel(),
                entity.getStatus().name(),
                entity.getSamplingNotes());
    }
}
