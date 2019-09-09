package com.carbonit.gpe.workshops.library_api.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AuthenticationException extends ResponseStatusException {
    public AuthenticationException(String msg) {
        super(HttpStatus.FORBIDDEN, msg);
    }
}
