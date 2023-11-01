package com.allan.videolocadora.enumeration.converter;

import com.allan.videolocadora.enumeration.EItemType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EItemTypeConverter implements AttributeConverter<EItemType, String> {

    @Override
    public String convertToDatabaseColumn(EItemType eItemType) {
        return eItemType == null ? null : eItemType.getValue();
    }

    @Override
    public EItemType convertToEntityAttribute(String value) {
        if(value == null){
            return null;
        }

        return Stream.of(EItemType.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public EItemType getStatusEnumValue(String value) {
        return switch (value) {
            case "Tape" -> EItemType.TAPE;
            case "DVD" -> EItemType.DVD;
            case "Blu-ray" -> EItemType.BLURAY;
            default -> throw new IllegalArgumentException("Invalid Item Type: " + value);
        };
    }
}
