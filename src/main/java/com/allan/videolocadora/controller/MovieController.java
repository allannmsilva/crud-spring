package com.allan.videolocadora.controller;

import java.util.List;

import com.allan.videolocadora.dto.MovieDTO;
import com.allan.videolocadora.service.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovieDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public MovieDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MovieDTO insert(@RequestBody @Valid MovieDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public MovieDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid MovieDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
