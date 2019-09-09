package com.carbonit.gpe.workshops.library_api.service;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.model.Validators;
import com.carbonit.gpe.workshops.library_api.service.exceptions.MissingRessourceException;
import com.carbonit.gpe.workshops.library_api.service.exceptions.NotModifiedException;
import com.carbonit.gpe.workshops.library_api.service.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FakeUserService implements IUserService {

    private final static AtomicLong USER_ID_GENERATOR = new AtomicLong(0L);
    private final static Map<Long, UserEntity> USER_REPOSITORY = new HashMap<>();

    @Override
    public IUser createUser(IUser userPartial) {
        Validators.validateEmail(userPartial.getEmail());
        UserEntity user = new UserEntity(userPartial, USER_ID_GENERATOR.incrementAndGet());
        USER_REPOSITORY.put(user.getUserId(), user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        nullSafeUser(USER_REPOSITORY.remove(userId));
    }

    @Override
    public IUser findById(Long userId) {
        return doFindById(userId);
    }

    private UserEntity doFindById(Long userId) {
        return nullSafeUser(USER_REPOSITORY.get(userId));
    }

    private UserEntity nullSafeUser(UserEntity user) {
        if (user == null)
            throw new MissingRessourceException("No registered user for this id");
        return user;
    }

    @Override
    public IUser updateUser(IUser userPartial) {
        Validators.validateUser(userPartial);

        UserEntity user = this.doFindById(userPartial.getUserId());
        boolean modified = false;

        if (userPartial.getEmail() != null && !Objects.equals(user.getEmail(), userPartial.getEmail())) {
            modified = true;
            user.setEmail(userPartial.getEmail());
        }
        if (userPartial.getName() != null && Objects.equals(user.getName(), userPartial.getName())) {
            modified = true;
            user.setName(userPartial.getName());
        }
        if (userPartial.getSurname() != null && Objects.equals(user.getSurname(), userPartial.getSurname())) {
            modified = true;
            user.setSurname(userPartial.getSurname());
        }

        if (!modified)
            throw new NotModifiedException("User in the registry was already up to date with the updates");
        return user;
    }
}
