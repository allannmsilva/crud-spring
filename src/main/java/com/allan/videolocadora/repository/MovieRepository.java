package com.allan.videolocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allan.videolocadora.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
