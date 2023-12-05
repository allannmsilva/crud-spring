package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ClassDTO;
import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.repository.ClassRepository;
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
public class ClassService implements ValidationService<ClassDTO> {

    private final ClassRepository repository;
    private final EntityMapper mapper;
    private final MovieService movieService;

    public ClassService(ClassRepository repository, EntityMapper mapper, MovieService movieService) {
        this.repository = repository;
        this.mapper = mapper;
        this.movieService = movieService;
    }

    public List<ClassDTO> getList() {
        return repository.findAll().stream().map(mapper::toClassDTO).toList();
    }

    public ClassDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toClassDTO).orElseThrow(
                () -> new RecordNotFoundException("Class not found!")
        );
    }

    public ClassDTO insert(@Valid @NotNull ClassDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toClassDTO(repository.save(mapper.toClassEntity(dto)));
    }

    public ClassDTO update(@NotNull @Positive Long id, @Valid @NotNull ClassDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(classFound -> {
                    classFound = mapper.toClassEntity(dto);
                    return mapper.toClassDTO(repository.save(classFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Class not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Class c = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Class not found!"));
        validateDelete(mapper.toClassDTO(c));
        repository.delete(c);
    }

    @Override
    public void validateInsertUpdate(ClassDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the class name!");
        }

        if (dto.returnDeadline() < 1) {
            throw new RequiredFieldException("You must enter the class return deadline!");
        }

        if (dto.worth() == 0.0d) {
            throw new RequiredFieldException("You must enter the class worth!");
        }
    }

    @Override
    public void validateDelete(ClassDTO dto) {
        for (MovieDTO movie : movieService.getList()) {
            if (movie.c().equals(dto)) {
                throw new IntegrityConstraintException("Class is being used in the movie " + movie.name() + "!");
            }
        }
    }
}
