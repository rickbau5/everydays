package week2;

import common.*;

/**
 * Created by Rick on 11/1/2016.
 */
public class Day07 {
    public static void main(String[] args) {
        Node<Character> list = SimpleList.of('a', 'b', 'c', 'd', 'e', 'f');
        System.out.println(SimpleList.stringify(list));
        deleteNode(list.next.next);
        System.out.println(SimpleList.stringify(list));
        System.out.println(kthToLast(list, 4));
        System.out.println(kthToLast_Rec(list, 4));

    }

    /* 2.2 Return kth To Last: Find and return the kth to the last element of a singly linked list */

    /**
     * Return kth to last using a naive solution. Counts nodes, then retraverses and returns kth to last.
     *
     * O(n), where n is length of list
     *
     * @param list the list
     * @param k index from the end
     * @param <T> the type of the values in the nodes
     * @return the value of the node at list size - k
     */
    static <T> T kthToLast(Node<T> list, int k) {
        int size = 0;
        for (Node<T> pntr = list; pntr != null; pntr = pntr.next) {
            size++;
        }
        size -= k;
        if (size < 0) {
            return null;
        }

        for (Node<T> pntr = list; pntr != null; pntr = pntr.next) {
            if (size-- == 0) {
                return pntr.value;
            }
        }

        return null;
    }

    /**
     * Return kth to the last element. Uses a recursive solution and Tuples.
     *
     * O(n), where n is length of the list.
     *  - computationally faster than the last one
     *  - requires more space, recursive calls.
     *
     * @param list The singly linked list
     * @param k the index from the end to delete
     * @param <T> the type of the node values
     * @return the value of the node at kth from the end
     */
    static <T> T kthToLast_Rec(Node<T> list, int k) {
        if (list == null) {
            return null;
        }
        Tuple<T, Integer> result = _kthToLast_Rec(list, k);
        if (result._2 == 0) {
            return result._1;
        } else {
            // It didn't get to zero, which means k is larger than the size of the list.
            return null;
        }
    }

    static <T> Tuple<T, Integer> _kthToLast_Rec(Node<T> list, int k) {
        if (list.next == null) {
            return new Tuple<>(list.value, k - 1);
        }
        Tuple<T, Integer> ret = _kthToLast_Rec(list.next, k);
        if (ret._2 == 0) {
            return ret;
        } else {
            ret._1 = list.value;
            ret._2--;
            return ret;
        }
    }

    /* 2.3 Delete Middle Node: Delete a node from the list that is not the first or last node. */

    /**
     * Given that the node is part of a list of size >= 3 and it is not the first or last node,
     * remove it from that list.
     *
     * @param node node to delete
     * @param <T> the type of the values in the node and list
     */
    static <T> void deleteNode(Node<T> node) {
        if (node == null || node.next == null)
            return;
        node.value = node.next.value;
        node.next = node.next.next;
    }
}
