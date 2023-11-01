package com.allan.videolocadora.repository;

import com.allan.videolocadora.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
