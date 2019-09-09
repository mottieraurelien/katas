package com.carbonit.gpe.workshops.library_api.service.authentication;


import com.carbonit.gpe.workshops.library_api.FeaturesConfig;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.IUserService;

import static com.carbonit.gpe.workshops.library_api.service.authentication.AuthenticationHelpers.*;

/**
 * In this class we are not compliant with JWT spec. We just encode the userId to base 64, expect the resulting string to be used as a JWT, and decode it the same way.
 * <p>
 * See {@link FullJwtService} for something which fits better the specification
 */
public class BasicJwtService implements IJwtService {
    private final IUserService userService;

    public BasicJwtService(IUserService userService) {
        this.userService = userService;
    }

    public Long deserializeUserIdFromAuthHeader(String authHeader) {
        if(FeaturesConfig.isJwtAuthenticationDisabled()){
            return FeaturesConfig.getDefaultUserId();
        }
        String token = stripTypeFromAuthHeader(authHeader, BEARER);
        return Long.valueOf(base64UrlDecode(token));
    }

    @Override
    public IUser jwtAuth(String authorizationHeader) {
        return this.userService.findById(deserializeUserIdFromAuthHeader(authorizationHeader));
    }

    @Override
    public String generateJwtFromUserId(String userId) {
        return base64UrlEncode(userId);
    }
}
