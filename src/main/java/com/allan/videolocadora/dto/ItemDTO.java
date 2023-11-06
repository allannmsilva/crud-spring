package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.EItemType;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public record ItemDTO(@JsonProperty("_id")
                      Long id,
                      @NotNull
                      MovieDTO movie,
                      @NotNull
                      int serialNumber,
                      @ValueOfEnum(enumClass = EItemType.class)
                      String type,
                      @NotNull
                      @Temporal(TemporalType.DATE)
                      Date acquisitionDate) {
}
