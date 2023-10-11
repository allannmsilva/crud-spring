package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.ActorDTO;
import com.allan.videolocadora.model.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    public ActorDTO toDto(Actor actor) {
        return actor == null ? null : new ActorDTO(actor.getId(), actor.getName());
    }

    public Actor toEntity(ActorDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ? new Actor(dto.id(), dto.name()) : new Actor(dto.name());
    }
}
