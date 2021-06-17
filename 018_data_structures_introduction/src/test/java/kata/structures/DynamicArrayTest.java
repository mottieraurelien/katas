package kata.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DynamicArrayTest {

    @Test
    void should_initialise_the_array_with_slots_when_creating_a_new_array_with_a_specific_dimension() {

        // [Arrange]
        final int inputLength = 3;

        // [Act]
        final DynamicArray actualDynamicArray = new DynamicArray(inputLength);

        // [Assert]
        assertThat(actualDynamicArray).isNotNull();
        assertThat(actualDynamicArray.getItems()).isNotNull();
        assertThat(actualDynamicArray.getItems()).hasSize(inputLength);
        assertThat(actualDynamicArray.getItems()).containsExactly(0, 0, 0);

    }

    @Test
    void should_append_to_array_when_adding_a_new_item_without_specifying_a_specific_index() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);

        // [Act]
        dynamicArray.insert(10);

        // [Assert]
        assertThat(dynamicArray).isNotNull();
        assertThat(dynamicArray.getItems()).isNotNull();
        assertThat(dynamicArray.getItems()).hasSize(inputLength);
        assertThat(dynamicArray.getItems()).containsExactly(10, 0, 0);

    }

    @Test
    void should_resize_the_array_when_adding_a_new_item_and_no_left_space() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);

        // [Act]
        dynamicArray.insert(40);

        // [Assert]
        assertThat(dynamicArray).isNotNull();
        assertThat(dynamicArray.getItems()).isNotNull();
        final int expectedLength = inputLength + inputLength;
        assertThat(dynamicArray.getItems()).hasSize(expectedLength);
        assertThat(dynamicArray.getItems()).containsExactly(10, 20, 30, 40, 0, 0);

    }

    @Test
    void should_not_move_any_item_when_removing_the_last_item_from_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);
        dynamicArray.insert(40);

        // [Act]
        dynamicArray.removeAt(3);

        // [Assert]
        assertThat(dynamicArray).isNotNull();
        assertThat(dynamicArray.getItems()).isNotNull();
        final int expectedLength = inputLength + inputLength;
        assertThat(dynamicArray.getItems()).hasSize(expectedLength);
        assertThat(dynamicArray.getItems()).containsExactly(10, 20, 30, 0, 0, 0);

    }

    @Test
    void should_throw_an_exception_when_trying_to_remove_an_item_from_an_index_that_is_not_contained_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);
        dynamicArray.insert(40);
        final int lastIndex = 3;

        // [Act / Assert]
        final int wrongIndex = 100;
        assertThatThrownBy(() -> dynamicArray.removeAt(wrongIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Index must be between [0," + lastIndex + "[.");

    }

    @Test
    void should_return_zero_when_finding_the_index_of_the_first_item() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);

        // [Act]
        final int actualIndex = dynamicArray.indexOf(10);

        // [Assert]
        assertThat(actualIndex).isEqualTo(0);

    }

    @Test
    void should_return_minus_one_when_trying_to_find_the_index_of_an_item_that_does_not_exist_in_the_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);

        // [Act]
        final int actualIndex = dynamicArray.indexOf(100);

        // [Assert]
        assertThat(actualIndex).isEqualTo(-1);

    }

    @Test
    void should_return_forty_when_finding_the_maximum_item_in_the_array() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);

        // [Act]
        final int actualMax = dynamicArray.max();

        // [Assert]
        assertThat(actualMax).isEqualTo(80);

    }

    @Test
    void should_return_an_empty_array_when_intersecting_an_empty_array_with_a_populated_one() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray emptyDynamicArray = new DynamicArray(0);

        // [Act]
        final DynamicArray actualDynamicArray = dynamicArray.intersect(emptyDynamicArray);

        // [Assert]
        assertThat(actualDynamicArray).isNotNull();
        assertThat(actualDynamicArray.getItems()).isEmpty();

    }

    @Test
    void should_return_an_empty_array_when_intersecting_a_populated_array_with_an_empty_one() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray emptyDynamicArray = new DynamicArray(0);

        // [Act]
        final DynamicArray actualDynamicArray = emptyDynamicArray.intersect(dynamicArray);

        // [Assert]
        assertThat(actualDynamicArray).isNotNull();
        assertThat(actualDynamicArray.getItems()).isEmpty();

    }

    @Test
    void should_return_an_array_with_common_items_only_when_intersecting_two_populated_arrays() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);
        final DynamicArray anotherDynamicArray = new DynamicArray(inputLength);
        anotherDynamicArray.insert(10);
        anotherDynamicArray.insert(20);
        anotherDynamicArray.insert(30);
        anotherDynamicArray.insert(40);
        anotherDynamicArray.insert(50);
        anotherDynamicArray.insert(60);
        anotherDynamicArray.insert(70);

        // [Act]
        final DynamicArray actualDynamicArray = anotherDynamicArray.intersect(dynamicArray);

        // [Assert]
        assertThat(actualDynamicArray).isNotNull();
        assertThat(actualDynamicArray.getItems()).hasSize(inputLength);
        assertThat(actualDynamicArray.getItems()).containsExactly(20, 50, 60, 0);

    }

    @Test
    void should_return_a_resized_array_with_reversed_values_when_reversing_an_array() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(60);

        // [Act]
        final DynamicArray actualDynamicArray = dynamicArray.reverse();

        // [Assert]
        assertThat(actualDynamicArray).isNotNull();
        assertThat(actualDynamicArray.getItems()).hasSize(4);
        assertThat(actualDynamicArray.getItems()).containsExactly(60, 80, 50, 20);

    }

    @Test
    void should_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);

        // [Act]
        dynamicArray.insertAt(100, 0);

        // [Assert]
        final int[] actualItems = dynamicArray.getItems();
        assertThat(actualItems).hasSize(4);
        assertThat(actualItems).containsExactly(100, 20, 50, 80);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_at_the_beginning_of_an_array_and_move_the_next_ones_when_inserting_the_item_at_index_zero() {

        // [Arrange]
        final int inputLength = 2;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(90);

        // [Act]
        dynamicArray.insertAt(100, 0);

        // [Assert]
        final int[] actualItems = dynamicArray.getItems();
        assertThat(actualItems).hasSize(6);
        assertThat(actualItems).containsExactly(100, 20, 50, 80, 90, 0);

    }

    @Test
    void should_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 4;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);

        // [Act]
        dynamicArray.insertAt(100, 2);

        // [Assert]
        final int[] actualItems = dynamicArray.getItems();
        assertThat(actualItems).hasSize(4);
        assertThat(actualItems).containsExactly(20, 50, 100, 80);

    }

    @Test
    void should_resize_the_array_plus_insert_an_item_in_the_middle_and_move_the_next_ones_when_inserting_the_item_int_middle() {

        // [Arrange]
        final int inputLength = 2;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(20);
        dynamicArray.insert(50);
        dynamicArray.insert(80);
        dynamicArray.insert(90);

        // [Act]
        dynamicArray.insertAt(100, 2);

        // [Assert]
        final int[] actualItems = dynamicArray.getItems();
        assertThat(actualItems).hasSize(6);
        assertThat(actualItems).containsExactly(20, 50, 100, 80, 90, 0);

    }

    @Test
    void should_throw_an_exception_when_insert_an_item_at_a_wrong_position() {

        // [Arrange]
        final int inputLength = 3;
        final DynamicArray dynamicArray = new DynamicArray(inputLength);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);
        dynamicArray.insert(40);

        // [Act / Assert]
        final int wrongIndex = 10;
        assertThatThrownBy(() -> dynamicArray.insertAt(99, wrongIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("The array is not large enough to welcome the item to insert at this specific index.");

    }

}