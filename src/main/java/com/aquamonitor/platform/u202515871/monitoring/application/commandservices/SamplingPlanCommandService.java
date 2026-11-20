package com.aquamonitor.platform.u202515871.monitoring.application.commandservices;

import com.aquamonitor.platform.u202515871.monitoring.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u202515871.shared.application.result.ApplicationError;
import com.aquamonitor.platform.u202515871.shared.application.result.Result;

public interface SamplingPlanCommandService {
    Result<SamplingPlan, ApplicationError> handle(CreateSamplingPlanCommand command);
}
