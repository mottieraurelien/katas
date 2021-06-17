package kata.structures;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class DynamicArray {

    private int[] items;
    private int nextFreeIndex;
    private final int initialCapacity;

    public DynamicArray(final int length) {
        this.initialCapacity = length;
        this.items = new int[length];
        this.nextFreeIndex = 0;
    }

    public DynamicArray(final int[] items) {
        this.initialCapacity = items.length;
        this.items = items;
        this.nextFreeIndex = items.length;
    }

    public void insert(final int item) {

        if (this.mustGrow()) {
            this.grow();
        }

        this.insertAt(item, this.nextFreeIndex);

    }

    public int indexOf(final int item) {
        for (int i = 0; i <= this.getLastIndex(); i++) {
            if (this.items[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(final int item) {
        return this.indexOf(item) > -1;
    }

    public void removeAt(final int index) {

        if (index > this.getLastIndex()) {
            throw new IllegalArgumentException("Index must be between [0," + this.getLastIndex() + "[.");
        }

        final int newLength = this.getLength();
        final int[] newItems = new int[newLength];
        int newItemsCounter = 0;
        for (int i = 0; i < this.getLastIndex(); i++) {
            if (i != index) {
                newItems[newItemsCounter] = this.items[i];
                newItemsCounter++;
            }
        }
        this.items = newItems;
        this.nextFreeIndex--;
    }

    public int max() {
        return stream(this.items)
                .max()
                .orElseThrow(() -> new UnsupportedOperationException("Could not find any max item."));
    }

    public DynamicArray intersect(final DynamicArray otherNumbers) {

        // Case 1 : one of them is empty so they cannot have anything in common :
        if (this.isEmpty() || otherNumbers.isEmpty()) {
            return new DynamicArray(new int[0]);
        }

        // Case 2 : both are not empty, meaning that we need to look over the items...
        // Let's be smart and look over the the smallest array > O(m), m being the number of item in the smallest array.
        final DynamicArray smallestDynamicArray = this.getLastIndex() < otherNumbers.getLastIndex() ? this : otherNumbers;
        final DynamicArray biggestDynamicArray = this.getLastIndex() < otherNumbers.getLastIndex() ? otherNumbers : this;

        // Best scenario : every smallestArray item are in the the biggestArray.
        final int[] intersectArray = new int[smallestDynamicArray.nextFreeIndex];
        int intersectCount = 0;

        // Let's look over the items now :
        for (int i = 0; i <= smallestDynamicArray.getLastIndex(); i++) {
            final int currentItem = smallestDynamicArray.items[i];
            if (biggestDynamicArray.contains(currentItem)) {
                intersectArray[intersectCount] = currentItem;
                intersectCount++;
            }
        }

        return new DynamicArray(intersectArray);

    }

    public DynamicArray reverse() {

        final DynamicArray reversedDynamicArray = new DynamicArray(this.nextFreeIndex);

        for (int i = this.getLastIndex(); i >= 0; i--) {
            final int currentItem = this.items[i];
            reversedDynamicArray.insert(currentItem);
        }

        return reversedDynamicArray;

    }

    public void insertAt(final int item, final int index) {

        if (index >= this.getLength()) {
            throw new IndexOutOfBoundsException("The array is not large enough to welcome the item to insert at this specific index.");
        }

        if (index <= this.getLastIndex()) {
            // We need to insert this new item in the "middle" of the array.
            // But can we move the next items ? Is the array large enough ?
            if (this.mustGrow()) {
                this.grow();
            }
            // We can now move the existing items to let the position empty for the new item we want to insert :
            if (this.getLastIndex() + 1 - index >= 0)
                arraycopy(this.items, index, this.items, index + 1, this.getLastIndex() + 1 - index);
        }

        this.items[index] = item;
        this.nextFreeIndex++;

    }

    public int[] getItems() {
        return this.items;
    }

    private int getLength() {
        return this.items.length;
    }

    private int getLastIndex() {
        return this.nextFreeIndex - 1;
    }

    private boolean isEmpty() {
        return this.nextFreeIndex == 0;
    }

    private boolean mustGrow() {
        return this.nextFreeIndex >= this.getLength();
    }

    private void grow() {
        final int currentLength = this.getLength();
        final int newLength = currentLength + this.initialCapacity;
        this.items = copyOf(this.items, newLength);
    }

}