package auth;

import io.dropwizard.auth.Authorizer;

import java.util.Objects;

public class DropwizardPartAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User principal, String role) {
        // Allow any logged in user.
        return Objects.nonNull(principal);
    }
}
