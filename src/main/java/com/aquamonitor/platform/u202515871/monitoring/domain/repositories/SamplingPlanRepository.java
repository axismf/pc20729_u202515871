package com.aquamonitor.platform.u202515871.monitoring.domain.repositories;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;

public interface SamplingPlanRepository {
    SamplingPlan save(SamplingPlan samplingPlan);

    boolean existsByPlanCode(PlanCode planCode);
}
