package com.allan.videolocadora.controller;

import java.util.List;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemDTO> getList() {
        return service.getList();
    }

    @GetMapping(value = "/{id}")
    public ItemDTO findById(@PathVariable @Positive @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO insert(@RequestBody @Valid ItemDTO dto) {
        return service.insert(dto);
    }

    @PutMapping(value = "/{id}")
    public ItemDTO update(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid ItemDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        service.delete(id);
    }
}
