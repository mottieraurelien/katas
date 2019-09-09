package com.carbonit.gpe.workshops.library_api.service.hateoas.impl;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static com.carbonit.gpe.workshops.library_api.service.hateoas.impl.HateoasReferenceService.buildReferenceLink;

@Relation(collectionRelation = "references")
public class EmbeddedReference extends ResourceSupport implements IReference {
    private String title;
    private String authorName;

    public EmbeddedReference(IReference model) {
        this.title = model.getTitle();
        this.authorName = model.getAuthorName();
        add(buildReferenceLink().slash(model.getReferenceId()).withSelfRel());
    }

    @JsonIgnore
    @Override
    public Long getReferenceId() {
        return null;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @JsonIgnore
    @Override
    public Boolean isBorrowed() {
        return null;
    }

    @Override
    public String getAuthorName() {
        return this.authorName;
    }
}
