package data;

import command.TwitterCommands;
import domain.TwitterUser;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TwitterUserRepository {

    private static final String REPOSITORY = "TwitterUserRepository";

    private final TwitterCommands commands;

    public TwitterUserRepository(final TwitterCommands commands) {
        this.commands = commands;
    }

    public void create(final TwitterUser user) {
        this.commands.hset(REPOSITORY, user.getEmail(), user.getPassword());
    }

    public Optional<TwitterUser> login(final String email, final String password) {
        String expectedPassword = this.commands.hget(REPOSITORY, email);
        if (expectedPassword.equals(password)) {
            return of(new TwitterUser(email, password, new ArrayList<>()));
        }
        return empty();
    }

}