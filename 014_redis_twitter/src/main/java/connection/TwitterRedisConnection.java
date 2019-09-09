package connection;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

public class TwitterRedisConnection {

    private static final RedisClient REDIS_CLIENT_INSTANCE = RedisClient.create("redis://165.227.168.143:6379/");
    private static final StatefulRedisConnection<String, String> REDIS_CONNECTION_INSTANCE = REDIS_CLIENT_INSTANCE
        .connect();
    private static final StatefulRedisPubSubConnection<String, String> REDIS_PUB_SUB_CONNECTION = REDIS_CLIENT_INSTANCE
        .connectPubSub();

    public StatefulRedisConnection getConnection() {
        return REDIS_CONNECTION_INSTANCE;
    }

    public StatefulRedisPubSubConnection getPubSubConnection() {
        return REDIS_PUB_SUB_CONNECTION;
    }

}