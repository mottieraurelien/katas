package com.carbonit.gpe.workshops.library_api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissingRessourceException extends ResponseStatusException {
    public MissingRessourceException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
