package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ActorDTO;
import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Actor;
import com.allan.videolocadora.repository.ActorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class ActorService implements ValidationService<ActorDTO> {

    private final ActorRepository repository;
    private final EntityMapper mapper;
    private final MovieService movieService;

    public ActorService(ActorRepository repository, EntityMapper mapper, MovieService movieService) {
        this.repository = repository;
        this.mapper = mapper;
        this.movieService = movieService;
    }

    public List<ActorDTO> getList() {
        return repository.findAll().stream().map(mapper::toActorDTO).toList();
    }

    public ActorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toActorDTO).orElseThrow(
                () -> new RecordNotFoundException("Actor not found!")
        );
    }

    public ActorDTO insert(@Valid @NotNull ActorDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toActorDTO(repository.save(mapper.toActorEntity(dto)));
    }

    public ActorDTO update(@NotNull @Positive Long id, @Valid @NotNull ActorDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(actorFound -> {
                    actorFound = mapper.toActorEntity(dto);
                    return mapper.toActorDTO(repository.save(actorFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Actor not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Actor actor = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Actor not found!"));
        validateDelete(mapper.toActorDTO(actor));
        repository.delete(actor);
    }

    @Override
    public void validateInsertUpdate(ActorDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the actor name!");
        }
    }

    @Override
    public void validateDelete(ActorDTO dto) {
        for (MovieDTO movie : movieService.getList()) {
            if (movie.cast().contains(mapper.toActorEntity(dto))) {
                throw new IntegrityConstraintException("Actor is in the cast of the movie " + movie.name() + "!");
            }
        }
    }
}
