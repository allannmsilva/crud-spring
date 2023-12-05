package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Item;
import com.allan.videolocadora.model.Movie;
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
    private final ItemService itemService;

    public MovieService(MovieRepository repository, EntityMapper mapper, ItemService itemService) {
        this.repository = repository;
        this.mapper = mapper;
        this.itemService = itemService;
    }

    public List<MovieDTO> getList() {
        return repository.findAll().stream().map(mapper::toMovieDTO).toList();
    }

    public MovieDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toMovieDTO).orElseThrow(
                () -> new RecordNotFoundException("Movie not found!")
        );
    }

    public MovieDTO insert(@Valid @NotNull MovieDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toMovieDTO(repository.save(mapper.toMovieEntity(dto)));
    }

    public MovieDTO update(@NotNull @Positive Long id, @Valid @NotNull MovieDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(movieFound -> {
                    movieFound = mapper.toMovieEntity(dto);
                    return mapper.toMovieDTO(repository.save(movieFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Movie not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Movie not found!"));
        validateDelete(mapper.toMovieDTO(movie));
        repository.delete(movie);
    }

    @Override
    public void validateInsertUpdate(MovieDTO dto) {
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

        if (dto.director() == null) {
            throw new RequiredFieldException("You must enter the movie's director!");
        }

        if (dto.c() == null) {
            throw new RequiredFieldException("You must enter the movie's class!");
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

    @Override
    public void validateDelete(MovieDTO dto) {
        for (ItemDTO item : itemService.getList()) {
            if (item.movie().equals(dto)) {
                throw new IntegrityConstraintException("Movie is present in the item " + item.serialNumber());
            }
        }
    }
}
