package com.carbonit.gpe.workshops.library_api.service;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;

import java.util.Collections;
import java.util.List;

public interface IUserService {
    IUser createUser(IUser userPartial);

    void deleteUser(Long userId);

    IUser findById(Long userId);

    IUser updateUser(IUser userPartial);

    static IUser anonymousUser(AuthorizationLevel authorizationLevel) {
        return new IUser() {
            @Override
            public Long getUserId() {
                return -1L;
            }

            @Override
            public String getName() {
                return "ANONYMOUS_USER";
            }

            @Override
            public String getSurname() {
                return "ANONYMOUS_USER";
            }

            @Override
            public String getEmail() {
                return "ANONYMOUS_USER@ANONYMO.US";
            }

            @Override
            public Boolean isLibrarian() {
                return null;
            }

            @Override
            public AuthorizationLevel getAuthorizationLevel() {
                return authorizationLevel;
            }

            @Override
            public List<? extends IReference> getBorrowing() {
                return Collections.emptyList();
            }
        };
    }
}
