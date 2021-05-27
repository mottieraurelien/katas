package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrayTest {

    @Test
    void should_initialise_the_array_with_slots_when_creating_a_new_array_with_a_specific_dimension() {

        // [Arrange]
        final int inputLength = 3;

        // [Act]
        final Array actualArray = new Array(inputLength);

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray.getItems()).isNotNull();
        assertThat(actualArray.getItems()).hasSize(inputLength);
        assertThat(actualArray.getItems()).containsExactly(0, 0, 0);

    }

    @Test
    void should_append_to_array_when_adding_a_new_item_without_specifying_a_specific_index() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);

        // [Act]
        array.insert(10);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.getItems()).isNotNull();
        assertThat(array.getItems()).hasSize(inputLength);
        assertThat(array.getItems()).containsExactly(10, 0, 0);

    }

    @Test
    void should_resize_the_array_when_adding_a_new_item_and_no_left_space() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        array.insert(40);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.getItems()).isNotNull();
        final int expectedLength = inputLength + inputLength;
        assertThat(array.getItems()).hasSize(expectedLength);
        assertThat(array.getItems()).containsExactly(10, 20, 30, 40, 0, 0);

    }

    @Test
    void should_not_move_any_item_when_removing_the_last_item_from_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);

        // [Act]
        array.removeAt(3);

        // [Assert]
        assertThat(array).isNotNull();
        assertThat(array.getItems()).isNotNull();
        final int expectedLength = inputLength + inputLength;
        assertThat(array.getItems()).hasSize(expectedLength);
        assertThat(array.getItems()).containsExactly(10, 20, 30, 0, 0, 0);

    }

    @Test
    void should_throw_an_exception_when_trying_to_remove_an_item_from_an_index_that_is_not_contained_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);
        final int lastIndex = 3;

        // [Act / Assert]
        final int wrongIndex = 100;
        assertThatThrownBy(() -> array.removeAt(wrongIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Index must be between [0," + lastIndex + "[.");

    }

    @Test
    void should_return_zero_when_finding_the_index_of_the_first_item() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        final int actualIndex = array.indexOf(10);

        // [Assert]
        assertThat(actualIndex).isEqualTo(0);

    }

    @Test
    void should_return_minus_one_when_trying_to_find_the_index_of_an_item_that_does_not_exist_in_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);

        // [Act]
        final int actualIndex = array.indexOf(100);

        // [Assert]
        assertThat(actualIndex).isEqualTo(-1);

    }

    @Test
    void should_return_forty_when_finding_the_maximum_item_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);

        // [Act]
        final int actualMax = array.max();

        // [Assert]
        assertThat(actualMax).isEqualTo(80);

    }

    @Test
    void should_return_an_empty_array_when_intersecting_an_empty_array_with_a_populated_one() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);
        final Array emptyArray = new Array(0);

        // [Act]
        final Array actualArray = array.intersect(emptyArray);

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray.getItems()).isEmpty();

    }

    @Test
    void should_return_an_empty_array_when_intersecting_a_populated_array_with_an_empty_one() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);
        final Array emptyArray = new Array(0);

        // [Act]
        final Array actualArray = emptyArray.intersect(array);

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray.getItems()).isEmpty();

    }

    @Test
    void should_return_an_array_with_common_items_only_when_intersecting_two_populated_arrays() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);
        final Array anotherArray = new Array(inputLength);
        anotherArray.insert(10);
        anotherArray.insert(20);
        anotherArray.insert(30);
        anotherArray.insert(40);
        anotherArray.insert(50);
        anotherArray.insert(60);
        anotherArray.insert(70);

        // [Act]
        final Array actualArray = anotherArray.intersect(array);

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray.getItems()).hasSize(inputLength);
        assertThat(actualArray.getItems()).containsExactly(20, 50, 60, 0);

    }

    @Test
    void should_return_a_resized_array_with_reversed_values_when_reversing_an_array() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(60);

        // [Act]
        final Array actualArray = array.reverse();

        // [Assert]
        assertThat(actualArray).isNotNull();
        assertThat(actualArray.getItems()).hasSize(4);
        assertThat(actualArray.getItems()).containsExactly(60, 80, 50, 20);

    }

    @Test
    void should_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);

        // [Act]
        array.insertAt(100, 0);

        // [Assert]
        final int[] actualItems = array.getItems();
        assertThat(actualItems).hasSize(4);
        assertThat(actualItems).containsExactly(100, 20, 50, 80);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 2;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(90);

        // [Act]
        array.insertAt(100, 0);

        // [Assert]
        final int[] actualItems = array.getItems();
        assertThat(actualItems).hasSize(6);
        assertThat(actualItems).containsExactly(100, 20, 50, 80, 90, 0);

    }

    @Test
    void should_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 4;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);

        // [Act]
        array.insertAt(100, 2);

        // [Assert]
        final int[] actualItems = array.getItems();
        assertThat(actualItems).hasSize(4);
        assertThat(actualItems).containsExactly(20, 50, 100, 80);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 2;
        final Array array = new Array(inputLength);
        array.insert(20);
        array.insert(50);
        array.insert(80);
        array.insert(90);

        // [Act]
        array.insertAt(100, 2);

        // [Assert]
        final int[] actualItems = array.getItems();
        assertThat(actualItems).hasSize(6);
        assertThat(actualItems).containsExactly(20, 50, 100, 80, 90, 0);

    }

    @Test
    void should_throw_an_exception_when_insert_an_item_at_a_wrong_position() {

        // [Arrange]
        final int inputLength = 3;
        final Array array = new Array(inputLength);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.insert(40);

        // [Act / Assert]
        final int wrongIndex = 10;
        assertThatThrownBy(() -> array.insertAt(99, wrongIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("The array is not large enough to welcome the item to insert at this specific index.");

    }

}