package com.carbonit.gpe.workshops.library_api.service.model;

import com.carbonit.gpe.workshops.library_api.model.IReference;

public class ReferenceEntity implements IReference {

    private Long referenceId = null;

    private String title = null;

    private boolean borrowed = false;

    private String authorName = null;

    public ReferenceEntity(IReference model) {
        this(model, model.getReferenceId());
    }

    public ReferenceEntity(IReference model, long referenceId) {
        this.referenceId = referenceId;
        this.title = model.getTitle();
        this.borrowed = model.isBorrowed() == null ? false : model.isBorrowed();
        this.authorName = model.getAuthorName();
    }

    @Override
    public Long getReferenceId() {
        return referenceId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }
}
