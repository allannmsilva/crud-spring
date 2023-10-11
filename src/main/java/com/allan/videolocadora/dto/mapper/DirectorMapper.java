package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {
    public DirectorDTO toDto(Director director) {
        return director == null ? null : new DirectorDTO(director.getId(), director.getName());
    }

    public Director toEntity(DirectorDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ? new Director(dto.id(), dto.name()) : new Director(dto.name());
    }
}
