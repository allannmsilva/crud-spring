package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.ClassDTO;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.model.Class;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {
    public ClassDTO toDto(Class c) {
        return c == null ? null : new ClassDTO(c.getId(), c.getName(), c.getWorth(), c.getDevolutionDate());
    }

    public Class toEntity(ClassDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ? new Class(dto.id(), dto.name(), dto.worth(), dto.devolutionDate()) : new Class(dto.name(), dto.worth(), dto.devolutionDate());
    }
}
