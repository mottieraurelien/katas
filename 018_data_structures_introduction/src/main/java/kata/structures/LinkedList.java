package kata.structures;

import kata.data.Node;

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

    public void addLast(final Node<T> node) {

        if (this.first == null) {
            this.first = this.last = node;
        } else {
            this.last.update(node);
            this.last = node;
        }

        this.size++;

    }

    public void addLast(final T item) {

        final Node<T> newLastNode = new Node<>(item, null);
        this.addLast(newLastNode);

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

    public T getKthFromTheBeginning(final int kth) {

        if (kth < 1 || kth > this.size() || isEmpty()) return null;

        Node<T> mainLead = this.first;

        for (int i = 0; i < kth - 1; i++) {
            mainLead = mainLead.getNext();
        }

        return mainLead.getValue();

    }

    public T getKthFromTheEnd(final int kth) {

        if (kth < 1 || kth > this.size || isEmpty()) return null;

        // Both nodes start from the beginning :
        Node<T> mainLead = this.first;
        Node<T> referenceTail = this.first;

        // We need to move the tail so that we have a gap of "kth" nodes between our lead and our tail :
        for (int i = 0; i < kth - 1; i++) {
            referenceTail = referenceTail.getNext();
        }
        // As this moment, we have :
        //  * our mainLead still at the first position,
        //  * our referenceTail "kth" nodes far from the mainLead.
        // If kth is 2 for instance, then mainLead[0] and referenceTail[2], and there is one node between them (1);

        // Now, our goal is to :
        //  * keep this distance between mainLead and referenceTail,
        //  * move our referenceTail until our referenceTail reaches the last node of our list.
        while (referenceTail != this.last) {
            mainLead = mainLead.getNext();
            referenceTail = referenceTail.getNext();
        }
        // If kth is 2 for instance then :
        //  * referenceTail is now at the end,
        //  * and mainLead at the position we wanted it to be.

        return mainLead.getValue();

    }

    @SuppressWarnings("unchecked")
    public Node<T>[] getMiddleNodes() {

        if (isEmpty()) return null;

        Node<T> lead = this.first;
        Node<T> tail = this.first;

        while (tail != this.last && tail.getNext() != this.last) {
            lead = lead.getNext();
            tail = tail.getNext().getNext();
        }

        if (tail == this.last) {
            return new Node[]{lead};
        }

        return new Node[]{lead, lead.getNext()};

    }

    public boolean isLooping() {

        if (isEmpty()) return false;

        Node<T> slowPointer = this.first;
        Node<T> fastPointer = slowPointer.getNext();

        while (slowPointer.getNext() != null) {
            if (slowPointer == fastPointer) {
                return true;
            }
            slowPointer = slowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();
        }

        return false;

    }

    boolean isEmpty() {
        return this.size == 0;
    }

}