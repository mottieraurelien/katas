package com.carbonit.gpe.workshops.library_api.service.model;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;

import java.util.ArrayList;
import java.util.List;

public class UserEntity implements IUser {

    private Long userId = null;

    private String name = null;

    private String surname = null;

    private String email = null;

    private Boolean librarian = false;

    private List<? extends IReference> borrowing = new ArrayList<>();

    public UserEntity(IUser model) {
        this(model, model.getUserId());
    }

    public UserEntity(IUser model, long userId) {
        this.userId = userId;
        this.name = model.getName();
        this.surname = model.getSurname();
        this.email = model.getEmail();
        this.librarian = model.getEmail() != null && model.getEmail().endsWith(".edu");
        if (model.getBorrowing() != null)
            this.borrowing = model.getBorrowing();
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Boolean isLibrarian() {
        return librarian;
    }

    @Override
    public List<? extends IReference> getBorrowing() {
        return borrowing;
    }
}
