package com.carbonit.gpe.workshops.library_api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataValidationException extends ResponseStatusException {
    public DataValidationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
