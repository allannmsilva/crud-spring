package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.ClassDTO;
import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.model.Director;
import com.allan.videolocadora.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieDTO toDto(Movie movie) {
        return movie == null ? null :
                new MovieDTO(movie.getId(), movie.getName(), movie.getYear(), movie.getSynopsis(),
                        movie.getCategory().getValue(), new DirectorDTO(movie.getDirector().getId(), movie.getDirector().getName()), new ClassDTO(movie.getC().getId(), movie.getC().getName(), movie.getC().getWorth(), movie.getC().getDevolutionDate()), movie.getCast());
    }

    public Movie toEntity(MovieDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ?
                new Movie(dto.id(), dto.name(), dto.year(),
                        dto.synopsis(), dto.category(), new Director(dto.director().id(), dto.director().name()), new Class(dto.c().id(), dto.c().name(), dto.c().worth(), dto.c().devolutionDate()), dto.cast())
                :
                new Movie(dto.name(), dto.year(), dto.synopsis(),
                        dto.category(), new Director(dto.director().id(), dto.director().name()), new Class(dto.c().id(), dto.c().name(), dto.c().worth(), dto.c().devolutionDate()), dto.cast());
    }
}
