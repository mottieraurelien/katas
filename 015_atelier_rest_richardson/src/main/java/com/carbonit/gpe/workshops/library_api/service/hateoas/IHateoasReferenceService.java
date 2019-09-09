package com.carbonit.gpe.workshops.library_api.service.hateoas;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.impl.EmbeddedReference;
import io.swagger.model.Reference;
import org.springframework.hateoas.Resources;

import java.util.List;

public interface IHateoasReferenceService {
    List<Reference> convertToHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel);

    Resources<EmbeddedReference> embbedHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel);

    Reference convertToHypermediaReference(IReference reference, AuthorizationLevel authLevel);
}
