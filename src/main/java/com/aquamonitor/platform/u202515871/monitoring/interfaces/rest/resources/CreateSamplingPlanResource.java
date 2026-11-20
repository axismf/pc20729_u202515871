package com.aquamonitor.platform.u202515871.monitoring.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CreateSamplingPlanResource {
    @Schema(example = "SAM-001")
    @NotBlank(message = "{validation.plan-code.not-blank}")
    private String planCode;

    @Schema(example = "550e8400-e29b-41d4-a716-446655440000")
    @NotNull(message = "{validation.station-id.not-null}")
    private UUID stationId;

    @Schema(example = "2026-11-21")
    @NotNull(message = "{validation.sampling-window-start-date.not-null}")
    private LocalDate samplingWindowStartDate;

    @Schema(example = "2026-11-25")
    @NotNull(message = "{validation.sampling-window-end-date.not-null}")
    private LocalDate samplingWindowEndDate;

    @Schema(example = "10")
    @NotNull(message = "{validation.sample-volume-quantity.not-null}")
    @Positive(message = "{validation.sample-volume-quantity.positive}")
    private Integer sampleVolumeQuantity;

    @Schema(example = "LITER")
    @NotBlank(message = "{validation.sample-volume-unit.not-blank}")
    private String sampleVolumeUnit;

    @Schema(example = "3")
    @NotNull(message = "{validation.priority-level.not-null}")
    @Min(value = 1, message = "{validation.priority-level.range}")
    @Max(value = 5, message = "{validation.priority-level.range}")
    private Integer priorityLevel;

    @Schema(allowableValues = {"PENDING", "APPROVED", "COMPLETED", "CANCELLED"}, example = "PENDING")
    @Setter(lombok.AccessLevel.NONE)
    @Pattern(regexp = "PENDING|APPROVED|COMPLETED|CANCELLED", message = "{validation.status.invalid}")
    private String status;

    @Schema(example = "Water quality sampling for river monitoring")
    private String samplingNotes;

    @JsonIgnore
    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    private boolean statusProvided;

    public void setStatus(String status) {
        this.status = status;
        this.statusProvided = true;
    }

    @JsonIgnore
    public boolean isStatusProvided() {
        return statusProvided;
    }

    @JsonIgnore
    @AssertTrue(message = "{validation.status.not-null}")
    public boolean isStatusNotNullWhenProvided() {
        return !statusProvided || status != null;
    }

    @JsonIgnore
    @AssertTrue(message = "{validation.sampling-window.end-after-start}")
    public boolean isSamplingWindowEndDateAfterStartDate() {
        return samplingWindowStartDate == null || samplingWindowEndDate == null
                || samplingWindowEndDate.isAfter(samplingWindowStartDate);
    }
}
