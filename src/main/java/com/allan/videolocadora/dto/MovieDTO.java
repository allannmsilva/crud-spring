package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.ECategory;
import com.allan.videolocadora.enumeration.validation.ValueOfEnum;
import com.allan.videolocadora.model.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record MovieDTO(@JsonProperty("_id")
                       Long id,
                       @NotNull @NotBlank @Length(min = 2, max = 100)
                       String name,
                       @NotNull
                       int year,
                       @NotNull @NotBlank
                       String synopsis,
                       @ValueOfEnum(enumClass = ECategory.class)
                       String category,
                       @NotNull
                       DirectorDTO director,
                       @NotNull
                       ClassDTO c,
                       @NotNull @NotEmpty @Valid
                       List<Actor> cast) {
}
