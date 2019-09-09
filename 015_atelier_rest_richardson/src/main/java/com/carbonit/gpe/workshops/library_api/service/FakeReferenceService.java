package com.carbonit.gpe.workshops.library_api.service;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.model.Validators;
import com.carbonit.gpe.workshops.library_api.service.exceptions.MissingRessourceException;
import com.carbonit.gpe.workshops.library_api.service.exceptions.NotModifiedException;
import com.carbonit.gpe.workshops.library_api.service.model.ReferenceEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class FakeReferenceService implements IReferenceService {

    private final static AtomicLong REFERENCE_ID_GENERATOR = new AtomicLong(0L);
    private final static Map<Long, ReferenceEntity> REFERENCES_REPOSITORY = new HashMap<>();

    @Override
    public IReference updateReferenceStatus(Long referenceId, boolean reserved, IUser user) {
        ReferenceEntity reference = this.doLoadReferenceById(referenceId);
        if (reference.isBorrowed() == reserved) {
            throw new NotModifiedException("Reference reservation status was already set to " + reserved);
        }
        reference.setBorrowed(reserved);
        List<IReference> references = (List<IReference>) user.getBorrowing();
        if (reserved) {
            references.add(reference);
        } else {
            references.remove(reference);
        }
        return reference;
    }

    @Override
    public IReference createReference(IReference body) {
        Validators.validateReference(body);
        ReferenceEntity reference = new ReferenceEntity(body, REFERENCE_ID_GENERATOR.incrementAndGet());
        REFERENCES_REPOSITORY.put(reference.getReferenceId(), reference);
        return reference;
    }

    @Override
    public void deleteReference(Long referenceId) {
        IReference removedReference = REFERENCES_REPOSITORY.remove(referenceId);
        if (removedReference == null)
            throw new MissingRessourceException("No reference to remove for id " + referenceId);
    }

    @Override
    public List<IReference> searchReference(String query) {
        return REFERENCES_REPOSITORY.values().stream()
                .filter(reference -> reference.toString().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public IReference loadReferenceById(Long referenceId) {
        return doLoadReferenceById(referenceId);
    }

    private ReferenceEntity doLoadReferenceById(Long referenceId) {
        ReferenceEntity reference = REFERENCES_REPOSITORY.get(referenceId);
        if (reference == null)
            throw new MissingRessourceException("No reference found for id " + referenceId);
        return reference;
    }

    @Override
    public IReference updateReference(IReference updatedReference) {
        Validators.validateReference(updatedReference);
        ReferenceEntity reference = this.doLoadReferenceById(updatedReference.getReferenceId());
        if (reference.equals(updatedReference)) {
            throw new NotModifiedException("Resource was already up to date");
        }
        if (updatedReference.getAuthorName() != null)
            reference.setAuthorName(updatedReference.getAuthorName());
        if (updatedReference.getTitle() != null)
            reference.setTitle(updatedReference.getTitle());
        return reference;
    }
}
