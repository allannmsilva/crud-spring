package com.allan.videolocadora.controller;

import java.util.List;

import com.allan.videolocadora.dto.ClassDTO;
import com.allan.videolocadora.service.ClassService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClassDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public ClassDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ClassDTO insert(@RequestBody @Valid ClassDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public ClassDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid ClassDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
