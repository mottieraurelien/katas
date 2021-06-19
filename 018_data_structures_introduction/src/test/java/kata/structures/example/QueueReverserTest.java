package kata.structures.example;

import kata.structures.CircularArrayQueue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueReverserTest {

    @Test
    void should_reverse_a_queue_when_dequeing_all_items_one_by_one() {

        // [Arrange]
        final QueueReverser<Integer> reverser = new QueueReverser<>(Integer.class);
        final CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // [Act]
        final Integer[] actual = reverser.reverse(queue);

        // [Assert]
        assertThat(actual).hasSize(3);
        assertThat(actual[0]).isEqualTo(30);
        assertThat(actual[1]).isEqualTo(20);
        assertThat(actual[2]).isEqualTo(10);

    }

}