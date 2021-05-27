package kata.structures;

import java.util.NoSuchElementException;

import static java.lang.reflect.Array.newInstance;

public class LinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    private final Class<T> itemClass;

    public LinkedList(final Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    public void addLast(final T item) {

        final Node<T> newLastNode = new Node<>(item, null);

        if (this.first == null) {
            this.first = this.last = newLastNode;
        } else {
            this.last.update(newLastNode);
            this.last = newLastNode;
        }

        this.size++;

    }

    public void addFirst(final T item) {

        if (this.first == null) {
            this.first = this.last = new Node<>(item, null);
        } else {
            this.first = new Node<>(item, this.first);
        }

        this.size++;

    }

    public void removeLast() {

        if (isEmpty()) throw new NoSuchElementException("Cannot remove the last item of an empty linked list.");

        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            Node<T> nextNode = this.first.getNext();
            while (nextNode.getNext() != this.last) {
                nextNode = nextNode.getNext();
            }
            nextNode.update(null);
            this.last = nextNode;
        }

        this.size--;

    }

    public void removeFirst() {

        if (isEmpty()) throw new NoSuchElementException("Cannot remove the first item of an empty linked list.");

        if (this.first == this.last) {
            this.first = this.last = null;
        } else {
            this.first = this.first.getNext();
        }

        this.size--;

    }

    public int indexOf(final T item) {
        int index = 0;
        Node<T> node = this.first;
        while (node != null) {
            if (node.getValue() == item) return index;
            node = node.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(final T item) {
        return this.indexOf(item) > -1;
    }

    public Node<T> getFirst() {
        return this.first;
    }

    public Node<T> getLast() {
        return this.last;
    }

    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public T[] values() {

        final T[] array = (T[]) newInstance(this.itemClass, size);

        if (isEmpty()) return array;

        int index = 0;
        Node<T> current = this.first;
        while (current != null) {
            array[index] = current.getValue();
            current = current.getNext();
            index++;
        }

        return array;
    }

    public void reverse() {

        if (isEmpty()) return;

        Node<T> previousNode = this.first;
        Node<T> currentNode = previousNode.getNext();
        previousNode.update(null);
        this.last = previousNode;

        while (currentNode != null) {
            previousNode = new Node<>(currentNode.getValue(), previousNode);
            currentNode = currentNode.getNext();
        }

        this.first = previousNode;

    }

    boolean isEmpty() {
        return this.size == 0;
    }

}