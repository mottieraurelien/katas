package com.carbonit.gpe.workshops.library_api.service.hateoas;

import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import io.swagger.model.User;

public interface IHateoasUserService {
    User convertToHypermediaUser(IUser user, AuthorizationLevel authLevel);
}
