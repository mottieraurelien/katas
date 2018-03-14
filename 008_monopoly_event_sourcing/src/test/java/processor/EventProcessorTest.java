package processor;

import event.GameEvent;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventProcessorTest {

    private static final String MESSAGE_LOG = "MESSAGE_LOG";

    @Test
    void given_a_game_event_to_process_triggers_the_event_processing_and_log_the_processed_event(
            @Mock GameEvent gameEventMock) {

        when(gameEventMock.process()).thenReturn(MESSAGE_LOG);
        EventProcessor eventProcessor = new EventProcessor(new ArrayList<>());

        eventProcessor.process(gameEventMock);

        verify(gameEventMock).process();
        assertTrue(eventProcessor.getLogs().contains(MESSAGE_LOG));

    }

}
