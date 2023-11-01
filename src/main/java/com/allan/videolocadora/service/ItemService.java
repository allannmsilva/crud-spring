package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.dto.mapper.ItemMapper;
import com.allan.videolocadora.enumeration.converter.EItemTypeConverter;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.repository.ItemRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final EItemTypeConverter converter;

    public ItemService(ItemRepository repository, ItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        converter = new EItemTypeConverter();
    }

    public List<ItemDTO> getList() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ItemDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public ItemDTO insert(@Valid @NotNull ItemDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ItemDTO update(@NotNull @Positive Long id, @Valid @NotNull ItemDTO dto) {
        return repository.findById(id) //
                .map(itemFound -> {
                    itemFound.setTitle(dto.title());
                    itemFound.setSerialNumber(dto.serialNumber());
                    itemFound.setItemType(converter.convertToEntityAttribute(dto.itemType()));
                    itemFound.setAcquisitionDate(dto.acquisitionDate());
                    return mapper.toDto(repository.save(itemFound));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
