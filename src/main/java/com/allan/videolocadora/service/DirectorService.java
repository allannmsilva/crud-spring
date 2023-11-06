package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.repository.DirectorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class DirectorService implements ValidationService<DirectorDTO> {

    private final DirectorRepository repository;
    private final EntityMapper mapper;

    public DirectorService(DirectorRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DirectorDTO> getList() {
        return repository.findAll().stream().map(mapper::toDirectorDTO).collect(Collectors.toList());
    }

    public DirectorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDirectorDTO).orElseThrow(
                () -> new RecordNotFoundException("Director not found!")
        );
    }

    public DirectorDTO insert(@Valid @NotNull DirectorDTO dto) {
        validateFields(dto);
        return mapper.toDirectorDTO(repository.save(mapper.toDirectorEntity(dto)));
    }

    public DirectorDTO update(@NotNull @Positive Long id, @Valid @NotNull DirectorDTO dto) {
        validateFields(dto);
        return repository.findById(id) //
                .map(directorFound -> {
                    directorFound = mapper.toDirectorEntity(dto);
                    return mapper.toDirectorDTO(repository.save(directorFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Director not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Director not found!")));
    }

    @Override
    public void validateFields(DirectorDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the name of the director!");
        }
    }
}
