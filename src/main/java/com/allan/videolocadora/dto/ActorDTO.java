package com.allan.videolocadora.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ActorDTO(@JsonProperty("_id")
                       Long id,
                       @NotNull @NotBlank @Length(min = 2, max = 100)
                       String name) {
}
