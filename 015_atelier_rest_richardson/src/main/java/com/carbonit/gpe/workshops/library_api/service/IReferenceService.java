package com.carbonit.gpe.workshops.library_api.service;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;

import java.util.List;

public interface IReferenceService {
    IReference updateReferenceStatus(Long referenceId, boolean reserved, IUser user);

    IReference createReference(IReference body);

    void deleteReference(Long referenceId);

    List<IReference> searchReference(String query);

    IReference loadReferenceById(Long referenceId);

    IReference updateReference(IReference updatedReference);
}
