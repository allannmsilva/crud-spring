package com.allan.videolocadora.controller;

import com.allan.videolocadora.dto.PartnerDTO;
import com.allan.videolocadora.service.PartnerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerService service;

    public PartnerController(PartnerService service) {
        this.service = service;
    }

    @GetMapping
    public List<PartnerDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public PartnerDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PartnerDTO insert(@RequestBody @Valid PartnerDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public PartnerDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid PartnerDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
