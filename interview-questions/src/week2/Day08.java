package week2;

import common.Node;
import common.SimpleList;

/**
 * Created by Rick on 11/4/16.
 */
public class Day08 {
    public static void main(String[] args) {
        System.out.println("---- Partition ----");
        doPartition(SimpleList.of(3, 5, 8, 5, 10, 2, 1), 5);
        doPartition(SimpleList.of(9, 4, 3, 1, 2, 10, 11, 15, 8, 7), 10);
        doPartition(SimpleList.of(1, 2, 3, 4, 5), 6);
        doPartition(SimpleList.of(1, 2, 3, 4, 5), 0);
    }

    /* 2.4 Partition: Implement a function to partition a list around a value x.                *\
     *                All nodes less than x should appear in the left partition, and all        *
     *                nodes greater than or equal to x should be in the right. The partition    *
    \*                value can appear anywhere in the right                                    */
    /**
     * Partition a list around the given value. For simplicity's sake, this function only accepts
     * Nodes with values of type Integer so we have access to the comparison operators.
     *
     * O(n), where n is the number of elements in the list.
     *
     * @param list the list to partition
     * @param value the partition value
     * @return the original list, but reordered by the following rules:
     *          a) if node value is less than passed value, then it is in the left partition
     *          b) if node value is greater than or equal to the passed value, it is in the right
     */
    static Node<Integer> partition(Node<Integer> list, int value) {
        if (list.next == null) {
            return list;
        }
        Node<Integer> beginLeft = null;
        Node<Integer> endLeft = null;
        Node<Integer> beginRight = null;
        Node<Integer> endRight = null;

        Node<Integer> pointer = list;

        while (pointer != null) {
            if (pointer.value < value) {
                if (beginLeft == null) {
                    beginLeft = pointer;
                    endLeft = beginLeft;
                } else {
                    endLeft.next = pointer;
                    endLeft = endLeft.next;
                }
            } else {
                if (beginRight == null) {
                    beginRight = pointer;
                    endRight = pointer;
                } else {
                    endRight.next = pointer;
                    endRight = endRight.next;
                }
            }
            pointer = pointer.next;
        }

        if (endRight != null)
            endRight.next = null;

        if (beginLeft != null) {
            if (beginRight != null) {
                endLeft.next = beginRight;
            }
            return beginLeft;
        } else {
            return beginRight;
        }
    }

    static void doPartition(Node<Integer> list, int value) {
        System.out.println(" " + SimpleList.stringify(list) + "\n " + SimpleList.stringify(partition(list, value)));
    }
}
