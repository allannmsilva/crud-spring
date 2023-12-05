package com.allan.videolocadora.controller;

import com.allan.videolocadora.dto.CustomerDTO;
import com.allan.videolocadora.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public CustomerDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerDTO insert(@RequestBody @Valid CustomerDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public CustomerDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid CustomerDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
