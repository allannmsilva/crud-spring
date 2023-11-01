package com.allan.videolocadora.dto;

import com.allan.videolocadora.enumeration.ECategory;
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
                       DirectorDTO director,
                       ClassDTO c,
                       @NotNull @NotEmpty @Valid
                       Set<Actor> cast) {
}
