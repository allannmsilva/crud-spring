package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.repository.ItemRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class ItemService implements ValidationService<ItemDTO> {

    private final ItemRepository repository;
    private final EntityMapper mapper;

    public ItemService(ItemRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ItemDTO> getList() {
        return repository.findAll().stream().map(mapper::toItemDTO).collect(Collectors.toList());
    }

    public ItemDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toItemDTO).orElseThrow(
                () -> new RecordNotFoundException("Item not found!")
        );
    }

    public ItemDTO insert(@Valid @NotNull ItemDTO dto) {
        return mapper.toItemDTO(repository.save(mapper.toItemEntity(dto)));
    }

    public ItemDTO update(@NotNull @Positive Long id, @Valid @NotNull ItemDTO dto) {
        return repository.findById(id) //
                .map(itemFound -> {
                    itemFound = mapper.toItemEntity(dto);
                    return mapper.toItemDTO(repository.save(itemFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Item not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Item not found!")));
    }

    @Override
    public void validateFields(ItemDTO dto) {
        if (dto.title() == null || dto.title().isBlank()) {
            throw new RequiredFieldException("You must enter the title of the item!");
        }

        if (dto.type() == null || dto.type().isBlank()) {
            throw new RequiredFieldException("You must enter the type of the item!");
        }

        if (dto.serialNumber() == 0) {
            throw new RequiredFieldException("You must enter the serial number of the item!");
        }

        if (dto.acquisitionDate() == null) {
            throw new RequiredFieldException("You must enter the acquisition date of the item!");
        }
    }
}
