import command.TwitterCommands;
import command.TwitterSyncCommands;
import connection.TwitterRedisConnection;
import data.TwitterTweetRepository;
import data.TwitterUserRepository;
import domain.TwitterTweet;
import domain.TwitterUser;
import java.util.ArrayList;
import java.util.Optional;

public class Twitter {

    public static void main(String[] args) {

        // Redis connection setup :
        TwitterRedisConnection twitterRedisConnection = new TwitterRedisConnection();
        TwitterCommands commands = new TwitterSyncCommands(twitterRedisConnection.getConnection().sync(),
            twitterRedisConnection.getPubSubConnection().reactive());
        TwitterUserRepository userRepository = new TwitterUserRepository(commands);
        TwitterTweetRepository tweetRepository = new TwitterTweetRepository(commands);

        // Creates some new users :
        TwitterUser john = new TwitterUser("john.doe@gmail.com", "password123", new ArrayList<>());
        userRepository.create(john);
        TwitterUser jane = new TwitterUser("jane.doe@gmail.com", "password456", new ArrayList<>());
        userRepository.create(jane);

        // On follow Jane :
        tweetRepository.follow(jane);
        tweetRepository.follow(john);

        // Login et tweet de John :
        Optional<TwitterUser> johnOptional = userRepository.login("john.doe@gmail.com", "password123");
        johnOptional
            .ifPresent(twitterUser -> tweetRepository.post(twitterUser, new TwitterTweet("C'est John qui tweet")));

        // Login et tweet de Jane :
        Optional<TwitterUser> janeOptional = userRepository.login("jane.doe@gmail.com", "password456");
        janeOptional.ifPresent(
            twitterUser -> tweetRepository.post(twitterUser, new TwitterTweet("Et là c'est Jane qui tweet")));

    }

}