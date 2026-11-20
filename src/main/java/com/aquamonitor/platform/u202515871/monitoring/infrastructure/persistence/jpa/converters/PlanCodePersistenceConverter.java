package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.converters;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class PlanCodePersistenceConverter implements AttributeConverter<PlanCode, String> {
    @Override
    public String convertToDatabaseColumn(PlanCode attribute) {
        return attribute == null ? null : attribute.code();
    }

    @Override
    public PlanCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new PlanCode(dbData);
    }
}
