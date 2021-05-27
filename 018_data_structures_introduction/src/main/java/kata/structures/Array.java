package kata.structures;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class Array {

    private int[] items;
    private int nextFreeSlot;

    private final int initLength;

    public Array(final int length) {
        this.initLength = length;
        this.items = new int[length];
        this.nextFreeSlot = 0;
    }

    public Array(final int[] items) {
        this.initLength = items.length;
        this.items = items;
        this.nextFreeSlot = items.length;
    }

    public void insert(final int item) {

        if (this.mustResizeBeforeInserting()) {
            this.resize();
        }

        this.insertAt(item, this.nextFreeSlot);

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

        final int newLength = this.getTechnicalLength();
        final int[] newItems = new int[newLength];
        int newItemsCounter = 0;
        for (int i = 0; i < this.getLastIndex(); i++) {
            if (i != index) {
                newItems[newItemsCounter] = this.items[i];
                newItemsCounter++;
            }
        }
        this.items = newItems;
        this.nextFreeSlot--;
    }

    public int max() {
        return stream(this.items)
                .max()
                .orElseThrow(() -> new UnsupportedOperationException("Could not find any max item."));
    }

    public Array intersect(final Array otherNumbers) {

        // Case 1 : one of them is empty so they cannot have anything in common :
        if (this.isEmpty() || otherNumbers.isEmpty()) {
            return new Array(new int[0]);
        }

        // Case 2 : both are not empty, meaning that we need to look over the items...
        // Let's be smart and look over the the smallest array > O(m), m being the number of item in the smallest array.
        final Array smallestArray = this.getLastIndex() < otherNumbers.getLastIndex() ? this : otherNumbers;
        final Array biggestArray = this.getLastIndex() < otherNumbers.getLastIndex() ? otherNumbers : this;

        // Best scenario : every smallestArray item are in the the biggestArray.
        final int[] intersectArray = new int[smallestArray.nextFreeSlot];
        int intersectCount = 0;

        // Let's look over the items now :
        for (int i = 0; i <= smallestArray.getLastIndex(); i++) {
            final int currentItem = smallestArray.items[i];
            if (biggestArray.contains(currentItem)) {
                intersectArray[intersectCount] = currentItem;
                intersectCount++;
            }
        }

        return new Array(intersectArray);

    }

    public Array reverse() {

        final Array reversedArray = new Array(this.nextFreeSlot);

        for (int i = this.getLastIndex(); i >= 0; i--) {
            final int currentItem = this.items[i];
            reversedArray.insert(currentItem);
        }

        return reversedArray;

    }

    public void insertAt(final int item, final int index) {

        if (index >= this.getTechnicalLength()) {
            throw new IndexOutOfBoundsException("The array is not large enough to welcome the item to insert at this specific index.");
        }

        if (index <= this.getLastIndex()) {
            // We need to insert this new item in the "middle" of the array.
            // But can we move the next items ? Is the array large enough ?
            if (this.mustResizeBeforeInserting()) {
                this.resize();
            }
            // We can now move the existing items to let the position empty for the new item we want to insert :
            if (this.getLastIndex() + 1 - index >= 0)
                arraycopy(this.items, index, this.items, index + 1, this.getLastIndex() + 1 - index);
        }

        this.items[index] = item;
        this.nextFreeSlot++;

    }

    public int[] getItems() {
        return this.items;
    }

    private int getTechnicalLength() {
        return this.items.length;
    }

    private int getRealLength() {
        return this.nextFreeSlot;
    }

    private int getLastIndex() {
        return this.nextFreeSlot - 1;
    }

    private boolean isEmpty() {
        return this.nextFreeSlot == 0;
    }

    private boolean mustResizeBeforeInserting() {
        return this.nextFreeSlot >= this.getTechnicalLength();
    }

    private void resize() {
        final int currentLength = this.getRealLength();
        final int newLength = currentLength + this.initLength;
        this.items = copyOf(this.items, newLength);
    }

}