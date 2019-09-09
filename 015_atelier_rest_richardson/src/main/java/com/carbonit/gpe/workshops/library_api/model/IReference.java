package com.carbonit.gpe.workshops.library_api.model;

public interface IReference {
    Long getReferenceId();

    String getTitle();

    Boolean isBorrowed();

    String getAuthorName();
}
