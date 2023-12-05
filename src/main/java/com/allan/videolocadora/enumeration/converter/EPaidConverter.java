package com.allan.videolocadora.enumeration.converter;

import com.allan.videolocadora.enumeration.EPaid;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EPaidConverter implements AttributeConverter<EPaid, String> {

    @Override
    public String convertToDatabaseColumn(EPaid ePaid) {
        return ePaid == null ? null : ePaid.getValue();
    }

    @Override
    public EPaid convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(EPaid.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public EPaid getPaidEnumValue(String value) {
        return switch (value) {
            case "Yes" -> EPaid.YES;
            case "No" -> EPaid.NO;
            default -> throw new IllegalArgumentException("Invalid Paid: " + value);
        };
    }
}
