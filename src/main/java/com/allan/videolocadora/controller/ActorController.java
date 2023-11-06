package com.allan.videolocadora.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.allan.videolocadora.dto.ActorDTO;
import com.allan.videolocadora.service.ActorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActorDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public ActorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActorDTO insert(@RequestBody @Valid ActorDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public ActorDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid ActorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
