package processor;

import event.GameEvent;

import java.util.Collections;
import java.util.List;

public class EventProcessor {

    private final List<String> logs;

    EventProcessor(final List<String> logs) {
        this.logs = logs;
    }

    public void process(final GameEvent gameEvent) {
        this.logs.add(gameEvent.process());
    }

    public List<String> getLogs() {
        return Collections.unmodifiableList(this.logs);
    }

}
