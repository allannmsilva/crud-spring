package com.allan.videolocadora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.allan.videolocadora.model.Movie;
import com.allan.videolocadora.repository.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public @ResponseBody List<Movie> getList() {
        return repository.findAll();
    }
}
