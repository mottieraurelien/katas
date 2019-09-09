package com.carbonit.gpe.workshops.library_api.service.authentication;

import com.carbonit.gpe.workshops.library_api.model.IUser;

public interface IAuthenticationService {
    String BASIC = "Basic";

    void registerUser(IUser user, String password);

    void updateUser(IUser user, String newPassword);

    IUser basicAuthenticateUser(String authorizationHeader);
}
