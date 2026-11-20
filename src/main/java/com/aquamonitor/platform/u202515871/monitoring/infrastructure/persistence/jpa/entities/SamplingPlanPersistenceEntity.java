package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.entities;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.converters.PlanCodePersistenceConverter;
import com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.converters.StationIdPersistenceConverter;
import com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.embeddables.SampleVolumePersistenceEmbeddable;
import com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.embeddables.SamplingWindowPersistenceEmbeddable;
import com.aquamonitor.platform.u202515871.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sampling_plans",
        uniqueConstraints = @UniqueConstraint(name = "uk_sampling_plans_plan_code", columnNames = "plan_code"))
public class SamplingPlanPersistenceEntity extends AuditableAbstractPersistenceEntity {
    @Convert(converter = PlanCodePersistenceConverter.class)
    @Column(name = "plan_code", nullable = false)
    private PlanCode planCode;

    @Convert(converter = StationIdPersistenceConverter.class)
    @Column(name = "station_id", nullable = false, length = 36)
    private StationId stationId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "sampling_window_start_date", nullable = false)),
            @AttributeOverride(name = "endDate", column = @Column(name = "sampling_window_end_date", nullable = false))})
    private SamplingWindowPersistenceEmbeddable samplingWindow;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "quantity", column = @Column(name = "sample_volume_quantity", nullable = false)),
            @AttributeOverride(name = "unit", column = @Column(name = "sample_volume_unit", nullable = false))})
    private SampleVolumePersistenceEmbeddable sampleVolume;

    @Column(name = "priority_level", nullable = false)
    private Integer priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private SamplingPlanStatus status;

    @Column(name = "sampling_notes", columnDefinition = "TEXT")
    private String samplingNotes;
}
