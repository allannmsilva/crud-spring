package com.allan.videolocadora.controller;

import java.util.List;

import com.allan.videolocadora.dto.DirectorDTO;
import com.allan.videolocadora.service.DirectorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService service;

    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @GetMapping
    public List<DirectorDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public DirectorDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DirectorDTO insert(@RequestBody @Valid DirectorDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public DirectorDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid DirectorDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
