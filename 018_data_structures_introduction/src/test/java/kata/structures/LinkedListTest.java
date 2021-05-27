package kata.structures;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkedListTest {

    @Test
    void should_add_the_item_at_the_first_position_when_adding_an_item_to_the_last_position_in_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act]
        linkedList.addLast(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualLastNode.getValue()).isEqualTo(5);
        assertThat(actualFirstNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(1);

    }

    @Test
    void should_append_the_item_to_the_linked_list_when_adding_the_item_at_last_position() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        linkedList.addLast(30);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(10);
        assertThat(actualSecondNode.getValue()).isEqualTo(20);
        assertThat(actualThirdNode.getValue()).isEqualTo(30);
        assertThat(actualThirdNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(3);

    }

    @Test
    void should_add_the_item_at_the_first_position_when_adding_an_item_to_the_first_position_in_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act]
        linkedList.addFirst(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualLastNode.getValue()).isEqualTo(5);
        assertThat(actualFirstNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(1);

    }

    @Test
    void should_add_the_item_to_the_beginning_of_the_linked_list_when_adding_the_item_at_first_position() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        linkedList.addFirst(5);

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualSecondNode.getValue()).isEqualTo(10);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualThirdNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(3);

    }

    @Test
    void should_throw_an_exception_when_trying_to_removing_the_first_item_from_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act / Assert]
        assertThatThrownBy(linkedList::removeFirst)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cannot remove the first item of an empty linked list.");

    }

    @Test
    void should_remove_the_item_at_the_first_position_when_removing_the_only_item_in_an_list_with_only_one_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addLast(5);

        // [Act]
        linkedList.removeFirst();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode).isNull();
        assertThat(actualLastNode).isNull();
        assertThat(linkedList.isEmpty()).isTrue();

    }

    @Test
    void should_remove_the_item_at_the_first_position_when_removing_the_first_item_of_a_populated_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addLast(5);
        linkedList.addLast(10);
        linkedList.addLast(15);
        linkedList.addLast(20);
        linkedList.addLast(30);

        // [Act]
        linkedList.removeFirst();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode.getValue()).isEqualTo(10);
        assertThat(actualSecondNode.getValue()).isEqualTo(15);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualFourthNode.getValue()).isEqualTo(30);
        assertThat(actualFifthNode).isNull();
        assertThat(actualFourthNode).isEqualTo(actualLastNode);
        assertThat(linkedList.size()).isEqualTo(4);

    }

    @Test
    void should_throw_an_exception_when_trying_to_remove_the_last_item_from_an_empty_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act / Assert]
        assertThatThrownBy(linkedList::removeLast)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cannot remove the last item of an empty linked list.");

    }

    @Test
    void should_remove_the_item_at_the_last_position_when_removing_the_only_item_in_an_list_with_only_one_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);

        // [Act]
        linkedList.removeLast();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualLastNode = linkedList.getLast();
        assertThat(actualFirstNode).isNull();
        assertThat(actualLastNode).isNull();
        assertThat(linkedList.isEmpty()).isTrue();

    }

    @Test
    void should_remove_the_item_at_the_last_position_when_removing_the_last_item_of_a_populated_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(30);
        linkedList.addFirst(20);
        linkedList.addFirst(15);
        linkedList.addFirst(10);
        linkedList.addFirst(5);

        // [Act]
        linkedList.removeLast();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();

        assertThat(actualFirstNode.getValue()).isEqualTo(5);
        assertThat(actualSecondNode.getValue()).isEqualTo(10);
        assertThat(actualThirdNode.getValue()).isEqualTo(15);
        assertThat(actualFourthNode.getValue()).isEqualTo(20);

        assertThat(actualFifthNode).isNull();
        assertThat(actualFourthNode).isEqualTo(actualLastNode);

        assertThat(linkedList.size()).isEqualTo(4);

    }

    @Test
    void should_return_false_when_the_list_does_not_contain_the_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final boolean actual = linkedList.contains(7);

        // [Assert]
        assertThat(actual).isFalse();

    }

    @Test
    void should_return_true_when_the_list_contains_the_item() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final boolean actual = linkedList.contains(10);

        // [Assert]
        assertThat(actual).isTrue();

    }

    @Test
    void should_return_minus_one_when_finding_the_index_of_an_item_that_is_not_in_the_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final int actual = linkedList.indexOf(7);

        // [Assert]
        assertThat(actual).isEqualTo(-1);

    }

    @Test
    void should_return_the_right_index_when_finding_the_index_of_an_item_that_is_in_the_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final int actual = linkedList.indexOf(5);

        // [Assert]
        assertThat(actual).isEqualTo(0);

    }

    @Test
    void should_return_an_empty_array_when_the_linked_list_is_empty() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act]
        final Integer[] actualArray = linkedList.values();

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray).isEmpty();

    }

    @Test
    void should_return_a_populated_array_when_the_linked_list_contains_a_node_at_least() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);

        // [Act]
        final Integer[] actualArray = linkedList.values();

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray)
                .extracting("value")
                .containsExactly(5, 10, 20);

    }

    @Test
    void should_not_do_anything_when_reversing_an_empty_linked_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);

        // [Act]
        linkedList.reverse();

        // [Assert]
        final Integer[] actualArray = linkedList.values();
        assertThat(actualArray).isNotNull();
        assertThat(actualArray).isEmpty();

    }

    @Test
    void should_move_all_items_when_reversing_a_populated_linked_list() {

        // [Arrange]
        final LinkedList<Integer> linkedList = new LinkedList<>(Integer.class);
        linkedList.addFirst(5);
        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        linkedList.addLast(40);

        // [Act]
        linkedList.reverse();

        // [Assert]
        final Node<Integer> actualFirstNode = linkedList.getFirst();
        final Node<Integer> actualSecondNode = actualFirstNode.getNext();
        final Node<Integer> actualThirdNode = actualSecondNode.getNext();
        final Node<Integer> actualFourthNode = actualThirdNode.getNext();
        final Node<Integer> actualFifthNode = actualFourthNode.getNext();
        final Node<Integer> actualLastNode = linkedList.getLast();

        assertThat(actualFirstNode.getValue()).isEqualTo(40);
        assertThat(actualSecondNode.getValue()).isEqualTo(30);
        assertThat(actualThirdNode.getValue()).isEqualTo(20);
        assertThat(actualFourthNode.getValue()).isEqualTo(10);
        assertThat(actualFifthNode.getValue()).isEqualTo(5);

        assertThat(actualFifthNode.getNext()).isNull();
        assertThat(actualFifthNode).isEqualTo(actualLastNode);

        assertThat(linkedList.size()).isEqualTo(5);

    }

}