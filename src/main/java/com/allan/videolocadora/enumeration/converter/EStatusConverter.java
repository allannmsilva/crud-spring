package com.allan.videolocadora.enumeration.converter;

import com.allan.videolocadora.enumeration.EStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EStatusConverter implements AttributeConverter<EStatus, String> {

    @Override
    public String convertToDatabaseColumn(EStatus eStatus) {
        return eStatus == null ? null : eStatus.getValue();
    }

    @Override
    public EStatus convertToEntityAttribute(String value) {
        if(value == null){
            return null;
        }

        return Stream.of(EStatus.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public EStatus getStatusEnumValue(String value) {
        return switch (value) {
            case "Active" -> EStatus.ACTIVE;
            case "Inactive" -> EStatus.INACTIVE;
            default -> throw new IllegalArgumentException("Invalid Status: " + value);
        };
    }
}
