package com.allan.videolocadora.controller;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Actor> findById(@PathVariable Long id) {
        return repository.findById(id) //
                .map(actorFound -> ResponseEntity.ok().body(actorFound)) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor actor) {
        return repository.findById(id) //
                .map(actorFound -> {
                    actorFound.setName(actor.getName());
                    Actor updated = repository.save(actorFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public /*ResponseEntity<Movie>*/ Actor insert(@RequestBody Actor actor) {
        return repository.save(actor);
        //return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(movie));
    }
}
