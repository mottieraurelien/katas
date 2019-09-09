package com.carbonit.gpe.workshops.library_api.service.hateoas.impl;

import com.carbonit.gpe.workshops.library_api.controller.UsersApiController;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasReferenceService;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasUserService;
import io.swagger.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService.GET_API_HOME;
import static com.carbonit.gpe.workshops.library_api.service.hateoas.impl.HateoasFacadeService.buildApiHomeLink;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Service
public class HateoasUserService implements IHateoasUserService {
    private static final String USERS_PATH = "users";

    static final String CREATE_JWT = "create_jwt";
    static final String CREATE_USER = "create_user";
    private static final String CONSULT_ANY_PROFILE = "view_by_userId";
    private static final String UPDATE_ANY_PROFILE = "patch_by_userId";
    private static final String DELETE_ANY_PROFILE = "delete_by_userId";
    private static final String UPDATE_PROFILE = "patch_profile";
    private static final String DELETE_PROFILE = "delete_profile";


    private final IHateoasReferenceService hateoasReferenceService;

    public HateoasUserService(IHateoasReferenceService hateoasReferenceService) {
        this.hateoasReferenceService = hateoasReferenceService;
    }

    private static ControllerLinkBuilder buildUserLink() {
        return linkTo(UsersApiController.class).slash(USERS_PATH);
    }

    @Override
    public User convertToHypermediaUser(IUser user, AuthorizationLevel authLevel) {
        // TODO: check TODO in User class
        User hypermediaUser = new User()
                .name(user.getName())
                .userId(user.getUserId())
                .borrowing(this.hateoasReferenceService.convertToHypermediaReferences(user.getBorrowing(), authLevel))
                .surname(user.getSurname())
                .email(user.getEmail())
                .librarian(user.isLibrarian());
        // TODO: add self reference link to hypermediaUser
        // TODO: add api home link to hypermediaUser
        // TODO: add contextual hypermedia links to hypermediaUser depending on user and authlevel
        return hypermediaUser;
    }

    private Iterable<Link> calculateLinks(IUser user, AuthorizationLevel authLevel){
        List<Link> links = new ArrayList<>();
        switch (authLevel){
            // TODO: calculate relevant links for given user and authLevel
        }
        return links;
    }
}
