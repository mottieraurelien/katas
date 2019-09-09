package command;

import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class TwitterSyncCommands implements TwitterCommands {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterSyncCommands.class);

    private final RedisCommands syncCommands;
    private final RedisPubSubReactiveCommands reactiveCommands;

    @Override
    public void hset(final String repository, final String key, final String value) {
        this.syncCommands.hset(repository, key, value);
    }

    @Override
    public String hget(final String repository, final String key) {
        return (String) this.syncCommands.hget(repository, key);
    }

    @Override
    public void subscribe(final String channel) {
        this.reactiveCommands.observeChannels()
            .subscribe(message -> {
                LOGGER.info("Message {} sur le channel {}", message, channel);
                System.out.println("Message [" + message + "] sur le channel [" + channel + "]");
            });
        this.reactiveCommands.subscribe(channel).subscribe();
    }

    @Override
    public void publish(final String channel, final String comment) {
        this.reactiveCommands.publish(channel, comment);
    }

}