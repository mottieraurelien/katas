package com.carbonit.gpe.workshops.library_api.service.hateoas;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.impl.EmbeddedReference;
import io.swagger.model.Reference;
import io.swagger.model.User;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import java.util.List;

public interface IHateoasService {
    String GET_API_HOME = "home";

    Reference convertToHypermediaReference(IReference reference, AuthorizationLevel authLevel);

    Resources<EmbeddedReference> embbedHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel);

    User convertToHypermediaUser(IUser user, AuthorizationLevel authLevel);

    ResourceSupport generateHypermediaApiHome(AuthorizationLevel currentLevel);

    ResourceSupport generateNoContentHypermedia();
}
