package com.carbonit.gpe.workshops.library_api.service.authorization;

import com.carbonit.gpe.workshops.library_api.model.IUser;

public interface IAuthorizationService {
    void checkLevel(IUser user, AuthorizationLevel level);
}
