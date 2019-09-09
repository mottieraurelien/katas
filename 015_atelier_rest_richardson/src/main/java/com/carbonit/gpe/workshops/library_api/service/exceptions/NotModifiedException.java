package com.carbonit.gpe.workshops.library_api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotModifiedException extends ResponseStatusException {
    public NotModifiedException(String reason) {
        super(HttpStatus.NOT_MODIFIED, reason);
    }
}
