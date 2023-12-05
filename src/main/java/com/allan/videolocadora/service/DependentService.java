package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.DependentDTO;
import com.allan.videolocadora.dto.LocationDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.enumeration.EStatus;
import com.allan.videolocadora.exception.*;
import com.allan.videolocadora.model.Dependent;
import com.allan.videolocadora.repository.DependentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@Service
public class DependentService implements ValidationService<DependentDTO> {

    private final DependentRepository repository;
    private final EntityMapper mapper;
    private final LocationService locationService;

    public DependentService(DependentRepository repository, EntityMapper mapper, LocationService locationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.locationService = locationService;
    }

    public List<DependentDTO> getList() {
        return repository.findAll().stream().map(mapper::toDependentDTO).toList();
    }

    public DependentDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toDependentDTO).orElseThrow(
                () -> new RecordNotFoundException("Dependent not found!")
        );
    }

    public DependentDTO insert(@Valid @NotNull DependentDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toDependentDTO(repository.save(mapper.toDependentEntity(dto)));
    }

    public DependentDTO update(@NotNull @Positive Long id, @Valid @NotNull DependentDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(dependentFound -> {
                    dependentFound = mapper.toDependentEntity(dto);
                    if (dependentFound.getPartner().getStatus().equals(EStatus.INACTIVE)) {
                        dependentFound.setStatus(EStatus.INACTIVE);
                    }
                    if (!Objects.equals(dto.status(), dependentFound.getStatus().getValue())) {
                        if (dependentFound.getStatus().equals(EStatus.ACTIVE) && repository.findAll().stream()
                                .filter(d -> d.getPartner().equals(mapper.toPartnerEntity(dto.partner())) &&
                                        d.getStatus().equals(EStatus.ACTIVE))
                                .toList().size() >= 3) {
                            throw new ThreeDependentsException();
                        }
                    } else {
                        if (dto.status().equals(EStatus.ACTIVE.getValue()) && repository.findAll().stream()
                                .filter(d -> d.getPartner().equals(mapper.toPartnerEntity(dto.partner())) &&
                                        d.getStatus().equals(EStatus.ACTIVE))
                                .toList().size() >= 3) {
                            throw new ThreeDependentsException();
                        }
                    }
                    return mapper.toDependentDTO(repository.save(dependentFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Dependent not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Dependent dependent = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Dependent not found!"));
        validateDelete(mapper.toDependentDTO(dependent));
        repository.delete(dependent);
    }

    @Override
    public void validateInsertUpdate(DependentDTO dto) {
        if (dto.status().equals("Inactive")) {
            for (LocationDTO locationDTO : locationService.getList().stream().filter(d -> d.customer().name().equals(dto.name())).toList()) {
                throw new IntegrityConstraintException("Dependent is being used in a location!");
            }
        }

        if(dto.status().equals("Active") &&  repository.findAll().stream()
                .filter(d -> d.getPartner().equals(mapper.toPartnerEntity(dto.partner())) &&
                        d.getStatus().equals(EStatus.ACTIVE))
                .toList().size() >= 3){
            throw new ThreeDependentsException();
        }

        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the dependent name!");
        }

        if (dto.name().length() < 2) {
            throw new FieldLengthException("Name of the dependent is too short!");
        }

        if (dto.name().length() > 100) {
            throw new FieldLengthException("Name of the dependent is too big!");
        }
    }

    @Override
    public void validateDelete(DependentDTO dto) {
        for (LocationDTO locationDTO : locationService.getList().stream().filter(d -> d.customer().name().equals(dto.name())).toList()) {
            throw new IntegrityConstraintException("Dependent is being used in a location!");
        }
    }
}
