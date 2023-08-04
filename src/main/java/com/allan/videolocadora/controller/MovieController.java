package com.allan.videolocadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repository.MovieRepository;

import model.Movie;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Movie> getList() {
        return repository.findAll();
    }

    @GetMapping
    public Movie findById(String id) {
        return null;
    }

    @PostMapping
    public void insert() {

    }

    @PutMapping
    public void update() {

    }

    @DeleteMapping
    public void delete() {

    }
}
