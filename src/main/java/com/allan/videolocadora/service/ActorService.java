package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ActorDTO;
import com.allan.videolocadora.dto.mapper.ActorMapper;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.repository.ActorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Service
public class ActorService {

    private final ActorRepository repository;
    private final ActorMapper mapper;

    public ActorService(ActorRepository repository, ActorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ActorDTO> getList() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ActorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public ActorDTO insert(@Valid @NotNull ActorDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ActorDTO update(@NotNull @Positive Long id, @Valid @NotNull ActorDTO dto) {
        return repository.findById(id) //
                .map(actorFound -> {
                    actorFound.setName(dto.name());
                    return mapper.toDto(repository.save(actorFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
