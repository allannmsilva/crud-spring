package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ClassDTO;
import com.allan.videolocadora.dto.mapper.ClassMapper;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.repository.ClassRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class ClassService {

    private final ClassRepository repository;
    private final ClassMapper mapper;

    public ClassService(ClassRepository repository, ClassMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ClassDTO> getList() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ClassDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public ClassDTO insert(@Valid @NotNull ClassDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ClassDTO update(@NotNull @Positive Long id, @Valid @NotNull ClassDTO dto) {
        return repository.findById(id) //
                .map(classFound -> {
                    classFound.setName(dto.name());
                    classFound.setWorth(dto.worth());
                    classFound.setDevolutionDate(dto.devolutionDate());
                    return mapper.toDto(repository.save(classFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
