package com.allan.videolocadora.controller;

import com.allan.videolocadora.dto.DependentDTO;
import com.allan.videolocadora.service.DependentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/dependents")
public class DependentController {

    private final DependentService service;

    public DependentController(DependentService service) {
        this.service = service;
    }

    @GetMapping
    public List<DependentDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public DependentDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DependentDTO insert(@RequestBody @Valid DependentDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public DependentDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid DependentDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
