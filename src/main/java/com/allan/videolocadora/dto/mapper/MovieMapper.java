package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieDTO toDto(Movie movie) {
        return movie == null ? null :
                new MovieDTO(movie.getId(), movie.getName(), movie.getYear(), movie.getSynopsis(),
                        movie.getCategory().getValue(), movie.getDirector(), movie.getC(), movie.getActors());
    }

    public Movie toEntity(MovieDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ?
                new Movie(dto.id(), dto.name(), dto.year(),
                        dto.synopsis(), dto.category(), dto.director(), dto.c(), dto.actors())
                :
                new Movie(dto.name(), dto.year(), dto.synopsis(),
                        dto.category(), dto.director(), dto.c(), dto.actors());
    }
}
