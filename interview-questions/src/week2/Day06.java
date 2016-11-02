package week2;

import common.Node;

import java.util.HashSet;

/**
 * Created by Rick on 10/31/2016.
 */
public class Day06 {
    public static void main(String[] args) {
        System.out.println("Hello world.");
        Node<Integer> list = new Node<>(1);
        list.append(1);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(1);
        list.append(6);
        list.append(5);

        list.foreach(System.out::println);

        System.out.println();
        removeDups(list);
        list.foreach(System.out::println);
    }

    /* 2.1 Remove Dups: Implement a method to remove duplicates from an unsorted linked list. */

    /**
     * Remove all duplicated in a list. Uses a hash set to keep track of present items.
     *
     * O(n), where n is the number of items in the list
     *
     * @param start Node to start at
     * @param <T> type of the values in the nodes
     */
    static <T> void removeDups(Node<T> start) {
        if (start == null)
            return;

        HashSet<T> set = new HashSet<>();
        Node<T> node = start;
        Node<T> last = start;
        while (node != null) {
            if (set.contains(node.value)) {
                last.next = node.next;
            } else {
                last = node;
                set.add(node.value);
            }
            node = node.next;
        }
    }
}
