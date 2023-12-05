package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.ItemDTO;
import com.allan.videolocadora.dto.LocationDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.enumeration.EPaid;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.model.Location;
import com.allan.videolocadora.repository.LocationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class LocationService implements ValidationService<LocationDTO> {

    private final LocationRepository repository;
    private final EntityMapper mapper;

    public LocationService(LocationRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<LocationDTO> getList() {
        return repository.findAll().stream().map(mapper::toLocationDTO).toList();
    }

    public LocationDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toLocationDTO).orElseThrow(
                () -> new RecordNotFoundException("Location not found!")
        );
    }

    public LocationDTO insert(LocationDTO dto) {
        validateInsertUpdate(dto);
        Location newLocation = mapper.toLocationEntity(dto);
        if (newLocation.getLocationDate() == null) {
            newLocation.setLocationDate(new Date());
        }
        Class locationClass = newLocation.getItem().getMovie().getC();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, locationClass.getReturnDeadline());
        if (newLocation.getEstimatedDevolutionDate() == null) {
            newLocation.setEstimatedDevolutionDate(c.getTime());
        }
        if (newLocation.getWorth() == 0.0d) {
            newLocation.setWorth(locationClass.getWorth());
        }
        newLocation.setFine(0.0);
        return mapper.toLocationDTO(repository.save(newLocation));
    }

    public LocationDTO update(@NotNull @Positive Long id, @Valid @NotNull LocationDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(locationFound -> {
                    locationFound = mapper.toLocationEntity(dto);
                    return mapper.toLocationDTO(repository.save(locationFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Location not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Location not found!"));
        validateDelete(mapper.toLocationDTO(location));
        repository.delete(location);
    }

    @Override
    public void validateInsertUpdate(LocationDTO dto) {
        if (dto.item() == null) {
            throw new RequiredFieldException("You must enter the location's item!");
        }

        if (dto.customer() == null) {
            throw new RequiredFieldException("You must enter the location's customer!");
        }

        if (dto.paid() == null) {
            throw new RequiredFieldException("You must enter if the location was paid or not!");
        }
    }

    @Override
    public void validateDelete(LocationDTO dto) {
    }
}
