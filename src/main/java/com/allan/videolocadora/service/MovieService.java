package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.repository.MovieRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class MovieService implements ValidationService<MovieDTO> {

    private final MovieRepository repository;
    private final EntityMapper mapper;
    private final ClassService classService;
    private final DirectorService directorService;

    public MovieService(MovieRepository repository, EntityMapper mapper, ClassService classService, DirectorService directorService) {
        this.repository = repository;
        this.mapper = mapper;
        this.classService = classService;
        this.directorService = directorService;
    }

    public List<MovieDTO> getList() {
        return repository.findAll().stream().map(mapper::toMovieDTO).collect(Collectors.toList());
    }

    public MovieDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toMovieDTO).orElseThrow(
                () -> new RecordNotFoundException("Movie not found!")
        );
    }

    public MovieDTO insert(@Valid @NotNull MovieDTO dto) {
        validateFields(dto);
        classService.findById(dto.c().id());
        directorService.findById(dto.director().id());
        return mapper.toMovieDTO(repository.save(mapper.toMovieEntity(dto)));
    }

    public MovieDTO update(@NotNull @Positive Long id, @Valid @NotNull MovieDTO dto) {
        validateFields(dto);
        classService.findById(dto.c().id());
        directorService.findById(dto.director().id());
        return repository.findById(id) //
                .map(movieFound -> {
                    movieFound = mapper.toMovieEntity(dto);
                    return mapper.toMovieDTO(repository.save(movieFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Movie not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Movie not found!")));
    }

    @Override
    public void validateFields(MovieDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the movie name!");
        }

        if (dto.category() == null || dto.category().isBlank()) {
            throw new RequiredFieldException("You must enter the movie's category!");
        }

        if (dto.synopsis() == null || dto.synopsis().isBlank()) {
            throw new RequiredFieldException("You must enter the movie's synopsis!");
        }

        if (dto.cast().isEmpty()) {
            throw new RequiredFieldException("You must enter the movie's cast!");
        }

        if (dto.name().length() < 2) {
            throw new FieldLengthException("Name of the movie is too short!");
        }

        if (dto.name().length() > 100) {
            throw new FieldLengthException("Name of the movie is too big!");
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);

        if (dto.year() <= 1894 || dto.year() > year) {
            throw new FieldLengthException("You must choose a year between 1895 and " + year);
        }
    }
}
