package com.carbonit.gpe.workshops.library_api.service.authentication;

import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.exceptions.AuthenticationException;

import static com.carbonit.gpe.workshops.library_api.FeaturesConfig.isJwtAuthenticationDisabled;
import static com.carbonit.gpe.workshops.library_api.service.IUserService.anonymousUser;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.PUBLIC;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.READER;

public interface IJwtService {
    String BEARER = "Bearer";

    IUser jwtAuth(String authorizationHeader);

    String generateJwtFromUserId(String userId);

    default IUser silentJwtAuth(String authorizationHeader) {
        if (isJwtAuthenticationDisabled()) {
            return anonymousUser(READER);
        }
        if (authorizationHeader == null || !authorizationHeader.trim().startsWith(BEARER)) {
            return anonymousUser(PUBLIC);
        }

        try {
            return jwtAuth(authorizationHeader);
        } catch (AuthenticationException e) {
            return anonymousUser(PUBLIC);
        }
    }
}
