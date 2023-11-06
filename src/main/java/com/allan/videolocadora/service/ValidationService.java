package com.allan.videolocadora.service;

import java.sql.SQLIntegrityConstraintViolationException;

public interface ValidationService<DTO> {

    void validateInsertUpdate(DTO dto);

    void validateDelete(DTO dto);
}
