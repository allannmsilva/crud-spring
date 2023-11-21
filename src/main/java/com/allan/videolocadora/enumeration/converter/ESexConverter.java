package com.allan.videolocadora.enumeration.converter;

import com.allan.videolocadora.enumeration.ESex;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ESexConverter implements AttributeConverter<ESex, String> {

    @Override
    public String convertToDatabaseColumn(ESex ESex) {
        return ESex == null ? null : ESex.getValue();
    }

    @Override
    public ESex convertToEntityAttribute(String value) {
        if(value == null){
            return null;
        }

        return Stream.of(ESex.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public ESex getSexEnumValue(String value) {
        return switch (value) {
            case "Male" -> ESex.MALE;
            case "Female" -> ESex.FEMALE;
            case "Undefined" -> ESex.UNDEFINED;
            default -> throw new IllegalArgumentException("Invalid Sex: " + value);
        };
    }
}
