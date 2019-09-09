package com.carbonit.gpe.workshops.library_api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthorizationException extends ResponseStatusException {
    public AuthorizationException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}
