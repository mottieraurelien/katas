package com.carbonit.gpe.workshops.library_api.controller;

import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.model.Validators;
import com.carbonit.gpe.workshops.library_api.service.IUserService;
import com.carbonit.gpe.workshops.library_api.service.authentication.IAuthenticationService;
import com.carbonit.gpe.workshops.library_api.service.authentication.IJwtService;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.authorization.IAuthorizationService;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService;
import io.swagger.annotations.ApiParam;
import io.swagger.api.UsersApi;
import io.swagger.model.JWT;
import io.swagger.model.User;
import io.swagger.model.UserPartial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

import static com.carbonit.gpe.workshops.library_api.controller.Converters.userPartialToUser;
import static com.carbonit.gpe.workshops.library_api.controller.ReferencesApiController.toLong;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.LIBRARIAN;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.PUBLIC;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.READER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final HttpServletRequest request;

    private final IUserService userService;

    private final IJwtService jwtService;

    private final IAuthenticationService authenticationService;

    private final IAuthorizationService authorizationService;

    private final IHateoasService hateoasService;


    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(HttpServletRequest request,
                              IUserService userService,
                              IJwtService jwtService,
                              IAuthenticationService authenticationService,
                              IAuthorizationService authorizationService,
                              IHateoasService hateoasService) {
        this.request = request;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
        this.hateoasService = hateoasService;
    }

    private ResponseEntity<User> buildResponseEntity(IUser user, AuthorizationLevel authLevel, HttpStatus status) {
        User hypermediaUser = this.hateoasService.convertToHypermediaUser(user, authLevel);
        return new ResponseEntity<User>(hypermediaUser, status);
    }

    public ResponseEntity<JWT> authUser() {
        String accept = request.getHeader("Accept");
        IUser user = this.authenticationService.basicAuthenticateUser(request.getHeader(AUTHORIZATION));
        String userId = String.valueOf(user.getUserId());
        return new ResponseEntity<JWT>(new JWT().jwt(this.jwtService.generateJwtFromUserId(userId)), OK);
    }

    public ResponseEntity<User> createUser(@ApiParam(value = "") @Valid @RequestBody UserPartial userPartial) {
        String accept = request.getHeader("Accept");
        Validators.validateCredentials(userPartial);
        IUser user = userService.createUser(userPartialToUser(userPartial));
        this.authenticationService.registerUser(user, userPartial.getPassword());
        return buildResponseEntity(user, PUBLIC, CREATED);
    }

    public ResponseEntity<ResourceSupport> deleteUser(@ApiParam(value = "The id of the targeted user.", required = true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        autorizeSelfUpdateOrEnforceLibrarian(userId);
        userService.deleteUser(toLong(userId));
        return new ResponseEntity<ResourceSupport>(this.hateoasService.generateNoContentHypermedia(), OK);
    }

    public ResponseEntity<User> showUser(@ApiParam(value = "The id of the targeted user.", required = true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        IUser currentUser = autorizeSelfUpdateOrEnforceLibrarian(userId);
        return buildResponseEntity(userService.findById(toLong(userId)), currentUser.getAuthorizationLevel(), OK);
    }

    public ResponseEntity<User> updateUser(@ApiParam(value = "The id of the targeted user.", required = true) @PathVariable("userId") String userId,
                                           @ApiParam(value = "") @Valid @RequestBody UserPartial userPartial) {
        String accept = request.getHeader("Accept");
        IUser currentUser = autorizeSelfUpdateOrEnforceLibrarian(userId);
        IUser updatedUser = userService.updateUser(userPartialToUser(userPartial, toLong(userId)));
        return buildResponseEntity(updatedUser, currentUser.getAuthorizationLevel(), OK);
    }

    private IUser autorizeSelfUpdateOrEnforceLibrarian(String userId) {
        IUser user = this.jwtService.jwtAuth(request.getHeader(AUTHORIZATION));
        boolean isSelfUpdating = Objects.equals(user.getUserId(), Long.valueOf(userId));
        this.authorizationService.checkLevel(user, isSelfUpdating ? READER : LIBRARIAN);
        return user;
    }
}
