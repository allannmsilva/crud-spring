package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.ECategory;
import com.allan.videolocadora.enumeration.EItemType;
import com.allan.videolocadora.enumeration.converter.EItemTypeConverter;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.allan.videolocadora.model.Actor;
import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.model.Director;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

public record ItemDTO(@JsonProperty("_id")
                      Long id,
                      @NotNull @NotBlank @Length(min = 2, max = 100)
                      String title,
                      @NotNull
                      int serialNumber,
                      @ValueOfEnum(enumClass = EItemType.class)
                      String itemType,
                      @NotNull
                      @Temporal(TemporalType.DATE)
                      Date acquisitionDate) {
}
