package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class SamplingWindowPersistenceEmbeddable {
    @Column(name = "sampling_window_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "sampling_window_end_date", nullable = false)
    private LocalDate endDate;

    public SamplingWindowPersistenceEmbeddable(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
