package com.carbonit.gpe.workshops.library_api.service.authentication;

import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.carbonit.gpe.workshops.library_api.service.exceptions.AuthenticationException;
import com.carbonit.gpe.workshops.library_api.service.exceptions.MissingRessourceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.carbonit.gpe.workshops.library_api.service.authentication.AuthenticationHelpers.*;

@Service
public class FakeAuthenticationService implements IAuthenticationService {

    private static final Map<IUser, String> credentialsMap = new HashMap<>();

    @Override
    public void registerUser(IUser user, String password) {
        credentialsMap.keySet().stream()
                .filter(user1 -> user1.getEmail().equals(user.getEmail()))
                .findFirst()
                .ifPresent(user1 -> {
                    throw new AuthenticationException("One user was already registered for this mail address");
                });
        credentialsMap.put(user, sha256(password));
    }

    @Override
    public void updateUser(IUser user, String newPassword) {
        if (credentialsMap.containsKey(user)) {
            credentialsMap.put(user, sha256(newPassword));
        } else {
            throw new MissingRessourceException("Could not update non existent user");
        }
    }

    @Override
    public IUser basicAuthenticateUser(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BASIC)) {
            throw new AuthenticationException("Authorization header did not contain a basic authentication");
        }
        String basicAuth = base64UrlDecode(stripTypeFromAuthHeader(authorizationHeader, BASIC));
        String[] tokens = basicAuth.split(":");
        String email = tokens[0];
        String password = tokens[1];

        return this.authenticate(email, password);
    }

    private IUser authenticate(String email, String password) {
        return credentialsMap.entrySet().stream()
                .filter(entry -> entry.getKey().getEmail().equals(email))
                .filter(entry -> entry.getValue().equals(sha256(password)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("Could not authenticate given credentials for email: " + email));
    }
}
