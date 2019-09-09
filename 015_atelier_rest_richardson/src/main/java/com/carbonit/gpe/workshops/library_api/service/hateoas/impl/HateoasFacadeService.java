package com.carbonit.gpe.workshops.library_api.service.hateoas.impl;

import com.carbonit.gpe.workshops.library_api.controller.ApiHomeController;
import com.carbonit.gpe.workshops.library_api.controller.ReferencesApiController;
import com.carbonit.gpe.workshops.library_api.controller.UsersApiController;
import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasReferenceService;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasUserService;
import io.swagger.model.Reference;
import io.swagger.model.User;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.carbonit.gpe.workshops.library_api.controller.ApiHomeController.API_HOME_PATH;
import static com.carbonit.gpe.workshops.library_api.service.hateoas.impl.HateoasReferenceService.*;
import static com.carbonit.gpe.workshops.library_api.service.hateoas.impl.HateoasUserService.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class HateoasFacadeService implements IHateoasService {

    private final IHateoasReferenceService hateoasReferenceService;

    private final IHateoasUserService hateoasUserService;

    public HateoasFacadeService(IHateoasReferenceService hateoasReferenceService, IHateoasUserService hateoasUserService) {
        this.hateoasReferenceService = hateoasReferenceService;
        this.hateoasUserService = hateoasUserService;
    }

    static ControllerLinkBuilder buildApiHomeLink() {
        return linkTo(ApiHomeController.class).slash(API_HOME_PATH);
    }

    @Override
    public Reference convertToHypermediaReference(IReference reference, AuthorizationLevel authLevel) {
        return this.hateoasReferenceService.convertToHypermediaReference(reference, authLevel);
    }

    @Override
    public Resources<EmbeddedReference> embbedHypermediaReferences(List<? extends IReference> references, AuthorizationLevel authLevel) {
        return this.hateoasReferenceService.embbedHypermediaReferences(references, authLevel);
    }

    @Override
    public User convertToHypermediaUser(IUser user, AuthorizationLevel authLevel) {
        return this.hateoasUserService.convertToHypermediaUser(user, authLevel);
    }

    @Override
    public ResourceSupport generateHypermediaApiHome(AuthorizationLevel currentLevel) {
        // TODO: carefully examine the code of this method as it hints for how to code int the other services
        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(buildApiHomeLink().withSelfRel());
        switch (currentLevel){
            case LIBRARIAN:{
                resourceSupport.add(linkTo(methodOn(ReferencesApiController.class).createReference(null)).withRel(CREATE_REFERENCE));
            }
            default:{
                resourceSupport.add(linkTo(methodOn(ReferencesApiController.class).searchReference(null)).withRel(SEARCH_REFERENCES));
                resourceSupport.add(linkTo(methodOn(UsersApiController.class).createUser(null)).withRel(CREATE_USER));
                resourceSupport.add(linkTo(methodOn(UsersApiController.class).authUser()).withRel(CREATE_JWT));
            }
        }
        return resourceSupport;
    }

    @Override
    public ResourceSupport generateNoContentHypermedia() {
        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(buildApiHomeLink().withRel(GET_API_HOME));
        return resourceSupport;
    }
}
