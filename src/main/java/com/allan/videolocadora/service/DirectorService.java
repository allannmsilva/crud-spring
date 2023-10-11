package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.dto.mapper.ActorMapper;
import com.allan.videolocadora.dto.mapper.DirectorMapper;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.repository.ActorRepository;
import com.allan.videolocadora.repository.DirectorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Service
public class DirectorService {

    private final DirectorRepository repository;
    private final DirectorMapper mapper;

    public DirectorService(DirectorRepository repository, DirectorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DirectorDTO> getList() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public DirectorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public DirectorDTO insert(@Valid @NotNull DirectorDTO director) {
        return mapper.toDto(repository.save(mapper.toEntity(director)));
    }

    public DirectorDTO update(@NotNull @Positive Long id, @Valid @NotNull DirectorDTO director) {
        return repository.findById(id) //
                .map(directorFound -> {
                    directorFound.setName(director.name());
                    return mapper.toDto(repository.save(directorFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
