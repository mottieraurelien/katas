package com.carbonit.gpe.workshops.library_api.controller;

import com.carbonit.gpe.workshops.library_api.service.authentication.IJwtService;
import com.carbonit.gpe.workshops.library_api.service.authorization.AuthorizationLevel;
import com.carbonit.gpe.workshops.library_api.service.hateoas.IHateoasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@Api(value = "api-home", description = "the home of the whole API")
@Controller
public class ApiHomeController {
    private static final Logger log = LoggerFactory.getLogger(ApiHomeController.class);
    public static final String API_HOME_PATH = "api-home";

    private final HttpServletRequest request;

    private final IJwtService jwtService;

    private final IHateoasService hateoasService;

    public ApiHomeController(HttpServletRequest request, IJwtService jwtService, IHateoasService hateoasService) {
        this.request = request;
        this.jwtService = jwtService;
        this.hateoasService = hateoasService;
    }

    @ApiOperation(value = "List available operations at home of the api", nickname = "listApiHomeOperations", notes = "", response = ResourceSupport.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The list of all available operations for the current User", response = ResourceSupport.class),
            @ApiResponse(code = 200, message = "unexpected error", response = Error.class)})
    @RequestMapping(value = "/" + API_HOME_PATH,
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<ResourceSupport> listApiHomeOperations() {
        AuthorizationLevel currentLevel = this.jwtService.silentJwtAuth(request.getHeader(AUTHORIZATION)).getAuthorizationLevel();
        ResourceSupport hypermediaHome = this.hateoasService.generateHypermediaApiHome(currentLevel);
        return new ResponseEntity<ResourceSupport>(hypermediaHome, OK);
    }
}
