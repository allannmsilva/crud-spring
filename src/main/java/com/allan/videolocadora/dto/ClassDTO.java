package com.allan.videolocadora.dto;

import com.allan.videolocadora.model.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

public record ClassDTO(@JsonProperty("_id")
                       Long id,
                       @NotNull @NotBlank @Length(min = 2, max = 100)
                       String name,
                       @NotNull
                       double worth,
                       @NotNull @Temporal(TemporalType.DATE)
                       Date devolutionDate) {
}
