package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.dto.PartnerDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Item;
import com.allan.videolocadora.model.Partner;
import com.allan.videolocadora.repository.PartnerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class PartnerService implements ValidationService<PartnerDTO> {

    private final PartnerRepository repository;
    private final EntityMapper mapper;

    public PartnerService(PartnerRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PartnerDTO> getList() {
        return repository.findAll().stream().map(mapper::toPartnerDTO).collect(Collectors.toList());
    }

    public PartnerDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toPartnerDTO).orElseThrow(
                () -> new RecordNotFoundException("Partner not found!")
        );
    }

    public PartnerDTO insert(@Valid @NotNull PartnerDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toPartnerDTO(repository.save(mapper.toPartnerEntity(dto)));
    }

    public PartnerDTO update(@NotNull @Positive Long id, @Valid @NotNull PartnerDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(partnerFound -> {
                    partnerFound = mapper.toPartnerEntity(dto);
                    return mapper.toPartnerDTO(repository.save(partnerFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Partner not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Partner partner = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Partner not found!"));
        validateDelete(mapper.toPartnerDTO(partner));
        repository.delete(partner);
    }

    @Override
    public void validateInsertUpdate(PartnerDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the partner name!");
        }

        if (dto.name().length() < 2) {
            throw new FieldLengthException("Name of the partner is too short!");
        }

        if (dto.name().length() > 100) {
            throw new FieldLengthException("Name of the partner is too big!");
        }
    }

    @Override
    public void validateDelete(PartnerDTO dto) {
    }
}