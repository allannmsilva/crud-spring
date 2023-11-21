package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.DependentDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Dependent;
import com.allan.videolocadora.repository.DependentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class DependentService implements ValidationService<DependentDTO> {

    private final DependentRepository repository;
    private final EntityMapper mapper;

    public DependentService(DependentRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DependentDTO> getList() {
        return repository.findAll().stream().map(mapper::toDependentDTO).collect(Collectors.toList());
    }

    public DependentDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDependentDTO).orElseThrow(
                () -> new RecordNotFoundException("Dependent not found!")
        );
    }

    public DependentDTO insert(@Valid @NotNull DependentDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toDependentDTO(repository.save(mapper.toDependentEntity(dto)));
    }

    public DependentDTO update(@NotNull @Positive Long id, @Valid @NotNull DependentDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(dependentFound -> {
                    dependentFound = mapper.toDependentEntity(dto);
                    return mapper.toDependentDTO(repository.save(dependentFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Dependent not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Dependent dependent = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Dependent not found!"));
        validateDelete(mapper.toDependentDTO(dependent));
        repository.delete(dependent);
    }

    @Override
    public void validateInsertUpdate(DependentDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the dependent name!");
        }

        if (dto.name().length() < 2) {
            throw new FieldLengthException("Name of the dependent is too short!");
        }

        if (dto.name().length() > 100) {
            throw new FieldLengthException("Name of the dependent is too big!");
        }
    }

    @Override
    public void validateDelete(DependentDTO dto) {
    }
}
