package common;

/**
 * Created by Rick on 11/1/2016.
 */
public class SimpleList {
    public static <T> Node<T> of(T ... values) {
        if (values == null) {
            return null;
        }

        Node<T> head = new Node<>(values[0]);
        Node<T> pntr = head;
        for (int i = 1; i < values.length; i++) {
            pntr.next = new Node<>(values[i]);
            pntr = pntr.next;
        }

        return head;
    }

    public static <T> String stringify(Node<T> list) {
        if (list == null) {
            return "List()";
        }

        StringBuilder builder = new StringBuilder("List(");
        list.foreachNode(v -> {
            builder.append(v.value);
            if (v.next != null)
                builder.append(", ");
        });
        builder.append(')');

        return builder.toString();
    }
}
