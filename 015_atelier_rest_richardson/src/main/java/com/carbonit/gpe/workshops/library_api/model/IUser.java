package com.carbonit.gpe.workshops.library_api.model;

import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.*;

public interface IUser {
    Long getUserId();

    String getName();

    String getSurname();

    String getEmail();

    Boolean isLibrarian();

    List<? extends IReference> getBorrowing();

    @JsonIgnore
    default AuthorizationLevel getAuthorizationLevel() {
        Boolean librarian = isLibrarian();
        return librarian == null ? PUBLIC :
                librarian ? LIBRARIAN : READER;
    }
}
