package com.allan.videolocadora.dto.mapper;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemDTO toDto(Item item) {
        return item == null ? null : new ItemDTO(item.getId(), item.getTitle(), item.getSerialNumber(), item.getItemType().getValue(), item.getAcquisitionDate());
    }

    public Item toEntity(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        return dto.id() != null ? new Item(dto.id(), dto.title(), dto.serialNumber(), dto.itemType(), dto.acquisitionDate()) : new Item(dto.title(), dto.serialNumber(), dto.itemType(), dto.acquisitionDate());
    }
}
