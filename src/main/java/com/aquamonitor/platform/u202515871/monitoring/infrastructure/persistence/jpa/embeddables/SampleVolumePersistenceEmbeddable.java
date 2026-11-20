package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class SampleVolumePersistenceEmbeddable {
    @Column(name = "sample_volume_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sample_volume_unit", nullable = false)
    private String unit;

    public SampleVolumePersistenceEmbeddable(Integer quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }
}
