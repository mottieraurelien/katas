package data;

import command.TwitterCommands;
import domain.TwitterTweet;
import domain.TwitterUser;

public class TwitterTweetRepository {

    private final TwitterCommands commands;

    public TwitterTweetRepository(final TwitterCommands commands) {
        this.commands = commands;
    }

    public void follow(final TwitterUser user) {
        this.commands.subscribe(user.getEmail());
    }

    public void post(final TwitterUser user, final TwitterTweet tweet) {
        this.commands.publish(user.getEmail(), tweet.getComment());
    }

}