package com.carbonit.gpe.workshops.library_api.service.authorization;

import com.carbonit.gpe.workshops.library_api.FeaturesConfig;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.exceptions.AuthorizationException;
import org.springframework.stereotype.Service;

import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.PUBLIC;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.READER;

@Service
public class SimpleAuthorizationService implements IAuthorizationService {
    private static final String ACCESS_DENIED = "Access Denied";

    @Override
    public void checkLevel(IUser user, AuthorizationLevel requiredLevel) {
        if (FeaturesConfig.isAuthorizationDisabled()) {
            return;
        }

        switch (user.getAuthorizationLevel()) {
            case LIBRARIAN:
                return;
            case READER: {
                if (requiredLevel == READER || requiredLevel == PUBLIC)
                    return;
                break;
            }
            default:
                if (requiredLevel == PUBLIC)
                    return;
        }
        throw new AuthorizationException(ACCESS_DENIED);
    }
}
