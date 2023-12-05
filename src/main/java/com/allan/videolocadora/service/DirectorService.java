package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Director;
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
    private final MovieService movieService;

    public DirectorService(DirectorRepository repository, EntityMapper mapper, MovieService movieService) {
        this.repository = repository;
        this.mapper = mapper;
        this.movieService = movieService;
    }

    public List<DirectorDTO> getList() {
        return repository.findAll().stream().map(mapper::toDirectorDTO).toList();
    }

    public DirectorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDirectorDTO).orElseThrow(
                () -> new RecordNotFoundException("Director not found!")
        );
    }

    public DirectorDTO insert(@Valid @NotNull DirectorDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toDirectorDTO(repository.save(mapper.toDirectorEntity(dto)));
    }

    public DirectorDTO update(@NotNull @Positive Long id, @Valid @NotNull DirectorDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(directorFound -> {
                    directorFound = mapper.toDirectorEntity(dto);
                    return mapper.toDirectorDTO(repository.save(directorFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Director not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Director director = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Director not found!"));
        validateDelete(mapper.toDirectorDTO(director));
        repository.delete(director);
    }

    @Override
    public void validateInsertUpdate(DirectorDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the name of the director!");
        }
    }

    @Override
    public void validateDelete(DirectorDTO dto) {
        for (MovieDTO movie : movieService.getList()) {
            if (movie.director().equals(dto)) {
                throw new IntegrityConstraintException("Director is acting in the movie " + movie.name() + "!");
            }
        }
    }
}
