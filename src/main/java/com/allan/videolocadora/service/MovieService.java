package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.MovieMapper;
import com.allan.videolocadora.enumeration.converter.ECategoryConverter;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.model.Actor;
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
    private ECategoryConverter converter;

    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
                    movieFound.setDirector(dto.director());
                    movieFound.getActors().clear();
                    movie.getActors().forEach(movieFound.getActors()::add);
                    return mapper.toDto(repository.save(movieFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
