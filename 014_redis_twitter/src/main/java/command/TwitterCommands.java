package command;

public interface TwitterCommands {

    void hset(final String repository, final String key, final String value);

    String hget(final String repository, final String key);

    void subscribe(final String channel);

    void publish(final String channel, final String comment);

}