package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.EPaid;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record LocationDTO(
        @JsonProperty("_id")
        Long id,
        @NotNull
        ItemDTO item,
        @NotNull
        CustomerDTO customer,
        double worth,
        double fine,

        @Temporal(TemporalType.DATE)
        Date estimatedDevolutionDate,
        @Temporal(TemporalType.DATE)
        Date devolutionDate,

        @Temporal(TemporalType.DATE)
        Date locationDate,
        @ValueOfEnum(enumClass = EPaid.class)
        String paid) {
}
