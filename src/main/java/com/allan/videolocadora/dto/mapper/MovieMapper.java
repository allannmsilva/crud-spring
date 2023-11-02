package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final ClassMapper classMapper = new ClassMapper();
    private final DirectorMapper directorMapper = new DirectorMapper();

    public MovieDTO toDto(Movie movie) {
        return movie == null ? null :
                new MovieDTO(movie.getId(), movie.getName(), movie.getYear(), movie.getSynopsis(),
                        movie.getCategory().getValue(), directorMapper.toDto(movie.getDirector()), classMapper.toDto(movie.getC()), movie.getCast());
    }

    public Movie toEntity(MovieDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ?
                new Movie(dto.id(), dto.name(), dto.year(),
                        dto.synopsis(), dto.category(), directorMapper.toEntity(dto.director()), classMapper.toEntity(dto.c()), dto.cast())
                :
                new Movie(dto.name(), dto.year(), dto.synopsis(),
                        dto.category(), directorMapper.toEntity(dto.director()), classMapper.toEntity(dto.c()), dto.cast());
    }
}
