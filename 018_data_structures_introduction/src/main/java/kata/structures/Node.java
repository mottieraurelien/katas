package kata.structures;

public class Node<T> {

    private final T value;
    private Node<T> next;

    public Node(final T value, final Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void update(final Node<T> newNextNode) {
        this.next = newNextNode;
    }

}