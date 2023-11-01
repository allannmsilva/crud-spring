package com.allan.videolocadora.enumeration.converter;

import com.allan.videolocadora.enumeration.ECategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.stream.Stream.of;

@Converter(autoApply = true)
public class ECategoryConverter implements AttributeConverter<ECategory, String> {

    @Override
    public String convertToDatabaseColumn(ECategory eCategory) {
        return eCategory == null ? null : eCategory.getValue();
    }

    @Override
    public ECategory convertToEntityAttribute(String value) {
        return switch (value) {
            case "Horror" -> ECategory.HORROR;
            case "Thriller" -> ECategory.THRILLER;
            case "Comedy" -> ECategory.COMEDY;
            case "Romance" -> ECategory.ROMANCE;
            default -> throw new IllegalArgumentException("Invalid Category: " + value);
        };
    }
}
