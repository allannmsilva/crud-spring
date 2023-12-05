package com.allan.videolocadora.controller;

import com.allan.videolocadora.dto.LocationDTO;
import com.allan.videolocadora.service.LocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<LocationDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public LocationDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocationDTO insert(@RequestBody @Valid LocationDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public LocationDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid LocationDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
