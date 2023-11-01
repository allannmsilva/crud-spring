package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.ClassMapper;
import com.allan.videolocadora.dto.mapper.DirectorMapper;
import com.allan.videolocadora.dto.mapper.MovieMapper;
import com.allan.videolocadora.enumeration.converter.ECategoryConverter;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.model.Director;
import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.model.Movie;
import com.allan.videolocadora.repository.MovieRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;
    private final ECategoryConverter converter;

    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        converter = new ECategoryConverter();
    }

    public List<MovieDTO> getList() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public MovieDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public MovieDTO insert(@Valid @NotNull MovieDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public MovieDTO update(@NotNull @Positive Long id, @Valid @NotNull MovieDTO dto) {
        return repository.findById(id) //
                .map(movieFound -> {
                    Movie movie = mapper.toEntity(dto);
                    movieFound.setName(dto.name());
                    movieFound.setYear(dto.year());
                    movieFound.setSynopsis(dto.synopsis());
                    movieFound.setCategory(converter.convertToEntityAttribute(dto.category()));
                    movieFound.setDirector(new Director(dto.director().id(), dto.director().name()));
                    movieFound.setC(new Class(dto.c().id(), dto.c().name(), dto.c().worth(), dto.c().devolutionDate()));
                    movieFound.getCast().clear();
                    movie.getCast().forEach(movieFound.getCast()::add);
                    return mapper.toDto(repository.save(movieFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
