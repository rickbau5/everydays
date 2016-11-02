package common;

import java.util.function.Consumer;

/**
 * Created by Rick on 10/31/2016.
 */
public class Node<T> {
    public Node<T> next;
    public T value;

    public Node(T value) {
        this.next = null;
        this.value = value;
    }

    public void append(T value) {
        this.append(new Node<>(value));
    }

    public void append(Node<T> node) {
        Node nextest = this;
        while (nextest.next != null)
            nextest = nextest.next;

        nextest.next = node;
    }

    public void foreach(Consumer<? super T> action) {
        action.accept(value);
        if (next != null) {
            next.foreach(action);
        }
    }

    public void foreachNode(Consumer<? super Node<T>> action) {
        action.accept(this);
        if (next != null) {
            next.foreachNode(action);
        }
    }
}
