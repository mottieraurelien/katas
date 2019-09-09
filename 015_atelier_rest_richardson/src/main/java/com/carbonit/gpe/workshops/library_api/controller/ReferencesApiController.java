package com.carbonit.gpe.workshops.library_api.controller;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.IReferenceService;
import com.carbonit.gpe.workshops.library_api.service.authentication.IJwtService;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.authorization.IAuthorizationService;
import com.carbonit.gpe.workshops.library_api.service.hateoas.impl.EmbeddedReference;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService;
import io.swagger.annotations.ApiParam;
import io.swagger.api.ReferencesApi;
import io.swagger.model.Reference;
import io.swagger.model.ReferencePartial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.carbonit.gpe.workshops.library_api.controller.Converters.referencePartialToReference;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.LIBRARIAN;
import static com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel.READER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class ReferencesApiController implements ReferencesApi {

    private static final Logger log = LoggerFactory.getLogger(ReferencesApiController.class);

    private final HttpServletRequest request;

    private final IReferenceService referenceService;

    private final IJwtService jwtService;

    private final IAuthorizationService authorizationService;

    private final IHateoasService hateoasService;


    @org.springframework.beans.factory.annotation.Autowired
    public ReferencesApiController(HttpServletRequest request,
                                   IReferenceService referenceService,
                                   IJwtService jwtService,
                                   IAuthorizationService authorizationService,
                                   IHateoasService hateoasService) {
        this.request = request;
        this.referenceService = referenceService;
        this.jwtService = jwtService;
        this.authorizationService = authorizationService;
        this.hateoasService = hateoasService;
    }

    public static final Long toLong(String longValue) {
        return Long.valueOf(longValue);
    }

    private IUser jwtToUser() {
        return this.jwtService.jwtAuth(request.getHeader(AUTHORIZATION));
    }

    private AuthorizationLevel computeCurrentLevel() {
        return this.jwtService.silentJwtAuth(request.getHeader(AUTHORIZATION)).getAuthorizationLevel();
    }

    private ResponseEntity<Reference> buildResponseEntity(IReference reference, AuthorizationLevel authorizationLevel, HttpStatus status) {
        Reference hypermediaReference = this.hateoasService.convertToHypermediaReference(reference, authorizationLevel);
        return new ResponseEntity<Reference>(hypermediaReference, status);
    }

    public ResponseEntity<Reference> borrowOrReturnReference(@ApiParam(value = "The id of the targeted reference.", required = true) @PathVariable("referenceId") String referenceId, @ApiParam(value = "") @Valid @RequestBody Boolean borrowed) {
        String accept = request.getHeader("Accept");
        IUser user = jwtToUser();
        this.authorizationService.checkLevel(user, READER);
        IReference updatedReference = referenceService.updateReferenceStatus(toLong(referenceId), borrowed, user);
        return buildResponseEntity(updatedReference, user.getAuthorizationLevel(), OK);
    }

    public ResponseEntity<Reference> createReference(@ApiParam(value = "") @Valid @RequestBody ReferencePartial body) {
        String accept = request.getHeader("Accept");
        this.authorizationService.checkLevel(jwtToUser(), LIBRARIAN);
        IReference createdReference = referenceService.createReference(referencePartialToReference(body));
        return buildResponseEntity(createdReference, LIBRARIAN, CREATED);
    }

    public ResponseEntity<Void> deleteReference(@ApiParam(value = "The id of the targeted reference.", required = true) @PathVariable("referenceId") String referenceId) {
        String accept = request.getHeader("Accept");
        this.authorizationService.checkLevel(jwtToUser(), LIBRARIAN);
        referenceService.deleteReference(toLong(referenceId));
        // TODO: hateoas endpoints should not return a 204. Make this one return at least a link to api home.
        // NB: you may take a look at how this is done in method deleteUser from UserApiController
        return new ResponseEntity<Void>(NO_CONTENT);
    }

    public ResponseEntity<Resources<EmbeddedReference>> searchReference(@ApiParam(value = "the search query") @Valid @RequestParam(value = "q", required = false) String q) {
        String accept = request.getHeader("Accept");
        String query = q == null ? "" : q;
        Resources<EmbeddedReference> referenceList = this.hateoasService
                .embbedHypermediaReferences(referenceService.searchReference(query), computeCurrentLevel());
        return new ResponseEntity(referenceList, OK);
    }

    public ResponseEntity<Reference> showReference(@ApiParam(value = "The id of the targeted reference.", required = true) @PathVariable("referenceId") String referenceId) {
        String accept = request.getHeader("Accept");
        IReference reference = referenceService.loadReferenceById(toLong(referenceId));
        return buildResponseEntity(reference, computeCurrentLevel(), OK);
    }

    public ResponseEntity<Reference> updateReference(@ApiParam(value = "The id of the targeted reference.", required = true) @PathVariable("referenceId") String referenceId, @ApiParam(value = "") @Valid @RequestBody Reference body) {
        String accept = request.getHeader("Accept");
        this.authorizationService.checkLevel(jwtToUser(), LIBRARIAN);
        return buildResponseEntity(referenceService.updateReference(body), LIBRARIAN, OK);
    }
}
