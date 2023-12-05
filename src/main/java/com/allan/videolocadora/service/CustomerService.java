package com.allan.videolocadora.service;

import com.allan.videolocadora.dto.CustomerDTO;
import com.allan.videolocadora.dto.LocationDTO;
import com.allan.videolocadora.dto.mapper.EntityMapper;
import com.allan.videolocadora.exception.FieldLengthException;
import com.allan.videolocadora.exception.IntegrityConstraintException;
import com.allan.videolocadora.exception.RecordNotFoundException;
import com.allan.videolocadora.exception.RequiredFieldException;
import com.allan.videolocadora.model.Customer;
import com.allan.videolocadora.repository.CustomerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.List;

@Validated
@Service
public class CustomerService implements ValidationService<CustomerDTO> {

    private final CustomerRepository repository;
    private final EntityMapper mapper;
    private final LocationService locationService;

    public CustomerService(CustomerRepository repository, EntityMapper mapper, LocationService locationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.locationService = locationService;
    }

    public List<CustomerDTO> getList() {
        return repository.findAll().stream().map(mapper::toCustomerDTO).filter(this::isAvailable).toList();
    }

    public CustomerDTO findById(@PathVariable @Positive @NotNull Long id) {
        return repository.findById(id).map(mapper::toCustomerDTO).orElseThrow(
                () -> new RecordNotFoundException("Customer not found!")
        );
    }

    public CustomerDTO insert(@Valid @NotNull CustomerDTO dto) {
        validateInsertUpdate(dto);
        return mapper.toCustomerDTO(repository.save(mapper.toCustomerEntity(dto)));
    }

    public CustomerDTO update(@NotNull @Positive Long id, @Valid @NotNull CustomerDTO dto) {
        validateInsertUpdate(dto);
        return repository.findById(id) //
                .map(customerFound -> {
                    customerFound = mapper.toCustomerEntity(dto);
                    return mapper.toCustomerDTO(repository.save(customerFound));
                })
                .orElseThrow(() -> new RecordNotFoundException("Customer not found!"));
    }

    public void delete(@NotNull @Positive Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found!"));
        validateDelete(mapper.toCustomerDTO(customer));
        repository.delete(customer);
    }

    @Override
    public void validateInsertUpdate(CustomerDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new RequiredFieldException("You must enter the customer name!");
        }

        if (dto.name().length() < 2) {
            throw new FieldLengthException("Name of the customer is too short!");
        }

        if (dto.name().length() > 100) {
            throw new FieldLengthException("Name of the customer is too big!");
        }
    }

    @Override
    public void validateDelete(CustomerDTO dto) {
        for (LocationDTO locationDTO : locationService.getList()) {
            if (locationDTO.customer().equals(dto)) {
                throw new IntegrityConstraintException("Customer is locating an item till " + new SimpleDateFormat("MM/dd/yyyy").format(locationDTO.devolutionDate()));
            }
        }
    }

    private boolean isAvailable(CustomerDTO dto) {
        if (dto.status().equals("Inactive")) {
            return false;
        }

        for (LocationDTO locationDTO : locationService.getList()) {
            if (locationDTO.customer().equals(dto) && (locationDTO.paid().equals("No") || locationDTO.devolutionDate() == null)) {
                return false;
            }
        }

        return true;
    }
}
