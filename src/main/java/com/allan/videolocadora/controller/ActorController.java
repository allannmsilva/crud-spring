package com.allan.videolocadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allan.videolocadora.model.Actor;
import com.allan.videolocadora.repository.ActorRepository;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorRepository repository;

    public ActorController(ActorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public @ResponseBody List<Actor> getList() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public /*ResponseEntity<Movie>*/ Actor insert(@RequestBody Actor actor) {
        return repository.save(actor);
        //return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(movie));
    }
}
