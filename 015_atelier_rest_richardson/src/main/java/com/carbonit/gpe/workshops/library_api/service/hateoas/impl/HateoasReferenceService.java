package com.carbonit.gpe.workshops.library_api.service.hateoas.impl;

import com.carbonit.gpe.workshops.library_api.controller.ReferencesApiController;
import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasReferenceService;
import io.swagger.model.Reference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService.GET_API_HOME;
import static com.carbonit.gpe.workshops.library_api.service.hateoas.impl.HateoasFacadeService.buildApiHomeLink;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class HateoasReferenceService implements IHateoasReferenceService {
    private static final String REFERENCES_PATH = "references";

    private static final String CONSULT_ANY_REFERENCE = "view_by_referenceId";
    static final String SEARCH_REFERENCES = "search";

    private static final String BORROW_REFERENCE = "put_borrowed_state";

    private static final String UPDATE_ANY_REFERENCE = "patch_by_referenceId";
    private static final String DELETE_ANY_REFERENCE = "delete_by_referenceId";
    static final String CREATE_REFERENCE = "create_reference";

    static ControllerLinkBuilder buildReferenceLink() {
        return linkTo(ReferencesApiController.class).slash(REFERENCES_PATH);
    }

    @Override
    public List<Reference> convertToHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel) {
        return references.stream().map(reference -> doConvertToHypermediaReference(reference, authLevel, false)).collect(Collectors.toList());
    }

    @Override
    public Resources<EmbeddedReference> embbedHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel) {
        Resources<EmbeddedReference> resources = new Resources<>(
                Collections.emptyList() // TODO: convert references list to EmbeddedReference. Be sure to understand why we have to do that
        );
        // TODO: add api home link to resources collection
        return resources;
    }

    @Override
    public Reference convertToHypermediaReference(IReference reference, AuthorizationLevel authLevel) {
        Reference hypermediaReference = doConvertToHypermediaReference(reference, authLevel, true);
        // TODO: add contextual hypermedia links to hypermediaReference depending on reference and authlevel
        return hypermediaReference;
    }

    private Reference doConvertToHypermediaReference(IReference reference, AuthorizationLevel authLevel, boolean includeHomeLink) {
        // TODO: check TODO in Reference class
        Reference hypermediaReference = new Reference()
                .authorName(reference.getAuthorName())
                .referenceId(reference.getReferenceId())
                .borrowed(reference.isBorrowed())
                .title(reference.getTitle())
                .referenceId(reference.getReferenceId());
        // TODO: add self reference link to hypermediaReference
        if (includeHomeLink) {
            // TODO: add api home link to hypermediaReference
        }
        return hypermediaReference;
    }

    private Iterable<Link> calculateLinks(IReference reference, AuthorizationLevel authLevel){
        List<Link> links = new ArrayList<>();
        switch (authLevel){
            // TODO: calculate relevant links for given reference and authLevel
        }
        return links;
    }
}
