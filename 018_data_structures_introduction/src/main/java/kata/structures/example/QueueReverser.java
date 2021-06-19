package kata.structures.example;

import kata.structures.CircularArrayQueue;
import kata.structures.StackDynamicArray;

import static java.lang.reflect.Array.newInstance;

public class QueueReverser<T> {

    private final Class<T> itemClass;

    public QueueReverser(final Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    @SuppressWarnings("unchecked")
    public T[] reverse(final CircularArrayQueue<T> queue) {

        if (queue.isEmpty()) return (T[]) newInstance(this.itemClass, 0);

        final int capacity = queue.size();
        final StackDynamicArray<T> stack = new StackDynamicArray<>(capacity);

        while (queue.isNotEmpty()) {
            final T item = queue.dequeue();
            stack.push(item);
        }

        int index = 0;
        final T[] items = (T[]) newInstance(this.itemClass, capacity);
        while (stack.isNotEmpty()) {
            final T item = stack.pop();
            items[index++] = item;
        }

        return items;

    }

}