package io.swagger.api;

import com.carbonit.gpe.workshops.library_api.service.hateoas.impl.EmbeddedReference;
import io.swagger.model.Reference;
import io.swagger.model.ReferencePartial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferencesApiControllerIntegrationTest {

    @Autowired
    private ReferencesApi api;

    @Test
    public void borrowOrReturnReferenceTest() throws Exception {
        String referenceId = "referenceId_example";
        Boolean body = true;
        ResponseEntity<Reference> responseEntity = api.borrowOrReturnReference(referenceId, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void createReferenceTest() throws Exception {
        ReferencePartial body = new ReferencePartial();
        ResponseEntity<Reference> responseEntity = api.createReference(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteReferenceTest() throws Exception {
        String referenceId = "referenceId_example";
        ResponseEntity<Void> responseEntity = api.deleteReference(referenceId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchReferenceTest() throws Exception {
        String q = "q_example";
        ResponseEntity<Resources<EmbeddedReference>> responseEntity = api.searchReference(q);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void showReferenceTest() throws Exception {
        String referenceId = "referenceId_example";
        ResponseEntity<Reference> responseEntity = api.showReference(referenceId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateReferenceTest() throws Exception {
        String referenceId = "referenceId_example";
        Reference body = new Reference();
        ResponseEntity<Reference> responseEntity = api.updateReference(referenceId, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
