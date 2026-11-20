package com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.transform;

import com.aquamonitor.platform.u202515871.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.resources.CreateSamplingPlanResource;

public final class CreateSamplingPlanCommandFromResourceAssembler {
    private CreateSamplingPlanCommandFromResourceAssembler() {
    }

    public static CreateSamplingPlanCommand toCommandFromResource(CreateSamplingPlanResource resource) {
        var status = resource.getStatus() == null ? null : SamplingPlanStatus.valueOf(resource.getStatus());
        return new CreateSamplingPlanCommand(
                resource.getPlanCode(),
                resource.getStationId(),
                resource.getSamplingWindowStartDate(),
                resource.getSamplingWindowEndDate(),
                resource.getSampleVolumeQuantity(),
                resource.getSampleVolumeUnit(),
                resource.getPriorityLevel(),
                status,
                resource.getSamplingNotes());
    }
}
