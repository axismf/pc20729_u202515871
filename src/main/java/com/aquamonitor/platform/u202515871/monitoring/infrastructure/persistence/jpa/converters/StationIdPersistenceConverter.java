package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.converters;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.StationId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = false)
public class StationIdPersistenceConverter implements AttributeConverter<StationId, String> {
    @Override
    public String convertToDatabaseColumn(StationId attribute) {
        return attribute == null ? null : attribute.value().toString();
    }

    @Override
    public StationId convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new StationId(UUID.fromString(dbData));
    }
}
