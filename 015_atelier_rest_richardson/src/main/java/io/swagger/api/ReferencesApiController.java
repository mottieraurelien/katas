package io.swagger.api;

import com.carbonit.gpe.workshops.library_api.service.hateoas.impl.EmbeddedReference;
import io.swagger.model.Reference;
import io.swagger.model.ReferencePartial;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-26T18:01:48.763Z[GMT]")
public class ReferencesApiController implements ReferencesApi {

    private static final Logger log = LoggerFactory.getLogger(ReferencesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ReferencesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Reference> borrowOrReturnReference(@ApiParam(value = "The id of the targeted reference.",required=true) @PathVariable("referenceId") String referenceId,@ApiParam(value = ""  )  @Valid @RequestBody Boolean body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Reference>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reference> createReference(@ApiParam(value = ""  )  @Valid @RequestBody ReferencePartial body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Reference>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteReference(@ApiParam(value = "The id of the targeted reference.",required=true) @PathVariable("referenceId") String referenceId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Resources<EmbeddedReference>> searchReference(@ApiParam(value = "the search query") @Valid @RequestParam(value = "q", required = false) String q) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Resources<EmbeddedReference>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reference> showReference(@ApiParam(value = "The id of the targeted reference.",required=true) @PathVariable("referenceId") String referenceId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Reference>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Reference> updateReference(@ApiParam(value = "The id of the targeted reference.",required=true) @PathVariable("referenceId") String referenceId,@ApiParam(value = ""  )  @Valid @RequestBody Reference body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Reference>(HttpStatus.NOT_IMPLEMENTED);
    }

}
