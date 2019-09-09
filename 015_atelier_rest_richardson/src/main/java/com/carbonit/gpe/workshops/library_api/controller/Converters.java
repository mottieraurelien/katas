package com.carbonit.gpe.workshops.library_api.controller;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import io.swagger.model.Reference;
import io.swagger.model.ReferencePartial;
import io.swagger.model.User;
import io.swagger.model.UserPartial;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    static User toUser(IUser entity) {
        return new User()
                .name(entity.getName())
                .userId(entity.getUserId())
                .borrowing(toReferences(entity.getBorrowing()))
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .librarian(entity.isLibrarian());

    }

    static List<Reference> toReferences(Collection<? extends IReference> references) {
        return references.stream().map(Converters::toReference).collect(Collectors.toList());
    }

    static Reference toReference(IReference reference) {
        return new Reference()
                .authorName(reference.getAuthorName())
                .referenceId(reference.getReferenceId())
                .borrowed(reference.isBorrowed())
                .title(reference.getTitle())
                .referenceId(reference.getReferenceId());
    }

    static IUser userPartialToUser(UserPartial userPartial) {
        return userPartialToUser(userPartial, null);
    }

    static IUser userPartialToUser(UserPartial userPartial, Long id) {
        return new IUser() {
            @Override
            public Long getUserId() {
                return id;
            }

            @Override
            public String getName() {
                return userPartial.getName();
            }

            @Override
            public String getSurname() {
                return userPartial.getSurname();
            }

            @Override
            public String getEmail() {
                return userPartial.getEmail();
            }

            @Override
            public Boolean isLibrarian() {
                return null;
            }

            @Override
            public List<? extends IReference> getBorrowing() {
                return null;
            }
        };
    }

    static IReference referencePartialToReference(ReferencePartial referencePartial) {
        return referencePartialToReference(referencePartial, null);
    }

    static IReference referencePartialToReference(ReferencePartial referencePartial, Long id) {
        return new IReference() {
            @Override
            public Long getReferenceId() {
                return id;
            }

            @Override
            public String getTitle() {
                return referencePartial.getTitle();
            }

            @Override
            public Boolean isBorrowed() {
                return null;
            }

            @Override
            public String getAuthorName() {
                return referencePartial.getAuthorName();
            }
        };
    }
}
