package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record CustomerDTO(
        @JsonProperty("_id")
        Long id,
        @NotNull @NotBlank @Length(min = 2, max = 100)
        String name,
        @NotNull @Temporal(TemporalType.DATE)
        Date birthDate,
        @ValueOfEnum(enumClass = ESex.class)
        String sex,
        @ValueOfEnum(enumClass = EStatus.class)
        String status
) {
}
