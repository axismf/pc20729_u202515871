package com.aquamonitor.platform.u202515871.monitoring.domain.model.aggregates;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SampleVolume;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SamplingWindow;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u202515871.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;

public class SamplingPlan extends AbstractDomainAggregateRoot<SamplingPlan> {
    public static final int MIN_PRIORITY_LEVEL = 1;

    public static final int MAX_PRIORITY_LEVEL = 5;

    private final Long id;
    private final PlanCode planCode;
    private final StationId stationId;
    private final SamplingWindow samplingWindow;
    private final SampleVolume sampleVolume;
    private final Integer priorityLevel;
    private final SamplingPlanStatus status;
    private final String samplingNotes;
    private final Instant createdAt;
    private final Instant updatedAt;

    public SamplingPlan(PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                        SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                        String samplingNotes) {
        this(null, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status, samplingNotes,
                null, null);
    }

    private SamplingPlan(Long id, PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                         SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                         String samplingNotes, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.planCode = requireValue(planCode, "Plan code");
        this.stationId = requireValue(stationId, "Station id");
        this.samplingWindow = requireValue(samplingWindow, "Sampling window");
        this.sampleVolume = requireValue(sampleVolume, "Sample volume");
        this.priorityLevel = validatePriorityLevel(priorityLevel);
        this.status = status == null ? SamplingPlanStatus.PENDING : status;
        this.samplingNotes = samplingNotes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static SamplingPlan reconstitute(Long id, PlanCode planCode, StationId stationId,
                                            SamplingWindow samplingWindow, SampleVolume sampleVolume,
                                            Integer priorityLevel, SamplingPlanStatus status, String samplingNotes,
                                            Instant createdAt, Instant updatedAt) {
        return new SamplingPlan(id, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status,
                samplingNotes, createdAt, updatedAt);
    }

    private static <V> V requireValue(V value, String name) {
        if (value == null) {
            throw new IllegalArgumentException("%s must not be null".formatted(name));
        }
        return value;
    }

    private static Integer validatePriorityLevel(Integer priorityLevel) {
        if (priorityLevel == null || priorityLevel < MIN_PRIORITY_LEVEL || priorityLevel > MAX_PRIORITY_LEVEL) {
            throw new IllegalArgumentException("Priority level must be between %d and %d"
                    .formatted(MIN_PRIORITY_LEVEL, MAX_PRIORITY_LEVEL));
        }
        return priorityLevel;
    }

    public Long getId() { return id; }

    public PlanCode getPlanCode() { return planCode; }

    public StationId getStationId() { return stationId; }

    public SamplingWindow getSamplingWindow() { return samplingWindow; }

    public SampleVolume getSampleVolume() { return sampleVolume; }

    public Integer getPriorityLevel() { return priorityLevel; }

    public SamplingPlanStatus getStatus() { return status; }

    public String getSamplingNotes() { return samplingNotes; }

    public Instant getCreatedAt() { return createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
}
