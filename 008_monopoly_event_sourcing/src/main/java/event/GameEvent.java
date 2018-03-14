package event;

@FunctionalInterface
public interface GameEvent {

    /**
     * Performs the related action and returns a message that can be logged to sum up the operation.
     */
    String process();

}
