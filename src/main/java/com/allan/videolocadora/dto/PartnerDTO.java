package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.ESex;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record PartnerDTO(
        @JsonProperty("_id")
        Long id,
        @NotNull @NotBlank @Length(min = 2, max = 100)
        String name,
        @NotNull @Temporal(TemporalType.DATE)
        Date birthDate,
        @ValueOfEnum(enumClass = ESex.class)
        String sex,
        @ValueOfEnum(enumClass = EStatus.class)
        String status,
        @NotNull @NotBlank @Length(min = 20, max = 200)
        String address,
        @NotNull @NotBlank @Length(min = 9, max = 9)
        String phone,
        @NotNull @NotBlank @Length(min = 11, max = 11)
        String cpf
) {
}
